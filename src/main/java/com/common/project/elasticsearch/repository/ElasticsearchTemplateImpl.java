package com.common.project.elasticsearch.repository;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.common.project.elasticsearch.util.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * program: esdemo
 * description: Elasticsearch基础功能组件实现类
 * author: X-Pacific zhang
 * create: 2019-01-18 16:04
 **/
@Component
@Slf4j
public class ElasticsearchTemplateImpl<T, M> implements ElasticsearchTemplate<T, M> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public PageList<Map<String, Object>> search(QueryBuilder queryBuilder, Attach attach,
            String indexs) throws Exception {
        if (attach == null) {
            throw new NullPointerException("Attach不能为空!");
        }
        PageList<Map<String, Object>> pageList = new PageList<>();
        List<Map<String, Object>> list = new ArrayList<>();
        PageSortHighLight pageSortHighLight = attach.getPageSortHighLight();
        SearchRequest searchRequest = new SearchRequest(indexs);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        boolean highLightFlag = false;
        boolean idSortFlag = false;
        if (pageSortHighLight != null) {
            //分页
            if (pageSortHighLight.getPageSize() != 0) {
                //search after不可指定from
                if (!attach.isSearchAfter()) {
                    searchSourceBuilder.from(pageSortHighLight.getStartPosition());
                }
                searchSourceBuilder.size(pageSortHighLight.getPageSize());
            }
            //排序
            if (pageSortHighLight.getSort() != null) {
                Sort sort = pageSortHighLight.getSort();
                List<Sort.Order> orders = sort.listOrders();
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getProperty().equals("_id")) {
                        idSortFlag = true;
                    }
                    searchSourceBuilder.sort(new FieldSortBuilder(orders.get(i).getProperty())
                            .order(orders.get(i).getDirection()));
                }
            }
            //高亮
            HighLight highLight = pageSortHighLight.getHighLight();
            if (highLight != null && highLight.getHighLightList() != null
                    && highLight.getHighLightList().size() != 0) {
                HighlightBuilder highlightBuilder = new HighlightBuilder();
                if (!StringUtils.isEmpty(highLight.getPreTag()) && !StringUtils
                        .isEmpty(highLight.getPostTag())) {
                    highlightBuilder.preTags(highLight.getPreTag());
                    highlightBuilder.postTags(highLight.getPostTag());
                }
                for (int i = 0; i < highLight.getHighLightList().size(); i++) {
                    highLightFlag = true;
                    // You can set fragment_size to 0 to never split any sentence.
                    //不对高亮结果进行拆分
                    highlightBuilder.field(highLight.getHighLightList().get(i), 0);
                }
                searchSourceBuilder.highlighter(highlightBuilder);
            }
        }
        //设定searchAfter
        if (attach.isSearchAfter()) {
            if (pageSortHighLight == null || pageSortHighLight.getPageSize() == 0) {
                searchSourceBuilder.size(10);
            } else {
                searchSourceBuilder.size(pageSortHighLight.getPageSize());
            }
            if (attach.getSortValues() != null && attach.getSortValues().length != 0) {
                searchSourceBuilder.searchAfter(attach.getSortValues());
            }
            //如果没拼_id的排序，自动添加保证排序唯一性
            if (!idSortFlag) {
                Sort.Order order = new Sort.Order(SortOrder.DESC, "upd_time");
                pageSortHighLight.getSort().and(new Sort(order));
                searchSourceBuilder.sort(new FieldSortBuilder("upd_time").order(SortOrder.DESC));
            }
        }

        //设定返回source
        if (attach.getExcludes() != null || attach.getIncludes() != null) {
            searchSourceBuilder.fetchSource(attach.getIncludes(), attach.getExcludes());
        }
        searchRequest.source(searchSourceBuilder);
        //设定routing
        if (!StringUtils.isEmpty(attach.getRouting())) {
            searchRequest.routing(attach.getRouting());
        }
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        Map map = null;

        for (SearchHit hit : searchHits) {
            //            T t = JsonUtils.string2Obj(hit.getSourceAsString(), clazz);
            String sourceAsString = hit.getSourceAsString();
           // map = JsonUtil.jsontoMap(sourceAsString);
            list.add(map);
        }

        pageList.setResultData(list);
        pageList.setTotalRecord(hits.getTotalHits());
        if (pageSortHighLight != null && pageSortHighLight.getPageSize() != 0) {
            pageList.setTotalPages(
                    getTotalPages(hits.getTotalHits(), pageSortHighLight.getPageSize()));
        }
        return pageList;
    }

    private int getTotalPages(long totalHits, int pageSize) {
        return pageSize == 0 ? 1 : (int) Math.ceil((double) totalHits / (double) pageSize);
    }

    public BulkByScrollResponse incrementCount(String indexName,String updateFieldName,Long count,String queryField,String queryFieldValue)throws IOException{
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put(updateFieldName,count);
        //创建 script
        StringBuilder sb = new StringBuilder();
        sb.append("ctx._source.").append(updateFieldName)
                .append("=(ctx._source.").append(updateFieldName).append("==null?0:ctx._source.").append(updateFieldName)//es 也要防止变量是null导致的空指针
                .append(")+params.").append(updateFieldName).append(";");//es字段名可以变量名可以不一样，此处为了方便，把字段名也当成变量名
        Script script = new Script(ScriptType.INLINE,"painless",sb.toString(),paramMap);
        //创建查询条件
        TermQueryBuilder tqb = new TermQueryBuilder(queryField,queryFieldValue);//通过term 语法，精确匹配查询
        UpdateByQueryRequest queryRequest = new UpdateByQueryRequest(indexName);
        queryRequest.setQuery(tqb);
        queryRequest.setScript(script);
        //设置版本冲突时继续
        queryRequest.setConflicts("proceed");
        queryRequest.setRefresh(true);
        BulkByScrollResponse bulkByScrollResponse = restHighLevelClient.updateByQuery(queryRequest,RequestOptions.DEFAULT);
        if(bulkByScrollResponse != null){
            log.info("索引[{}]成功更新[{}]文档",indexName,bulkByScrollResponse.getUpdated());
        }
        return bulkByScrollResponse;
    }
}

