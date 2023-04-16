package com.common.project.elasticsearch.repository;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;

import java.io.IOException;
import java.util.Map;

/**
 * 使用开源代码 https://gitee.com/zxporz/ESClientRHL/wikis/Elasticsearch-ESClientRHL
 **/
public interface ElasticsearchTemplate<T, M> {

    PageList<Map<String, Object>> search(QueryBuilder queryBuilder, Attach attach, String indexs)
            throws Exception;

    /** 根据查询条件，累加指定字段
     * @param indexName 索引名
     * @param updateFieldName 要更新的字段
     * @param count 每次请求，累计多少
     * @param queryField 查询字段
     * @param queryFieldValue 查询字段id
     * @return 更新文档数量
     * @throws IOException 抛出异常
     */
    BulkByScrollResponse incrementCount(String indexName, String updateFieldName, Long count,
            String queryField, String queryFieldValue) throws IOException;
}
