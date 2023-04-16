package com.common.project.generator.base;

import com.baomidou.mybatisplus.core.metadata.IPage;


/**
 * controller 抽象类
 *
 * @date 2019-03-14
 */
public abstract class AbstractController<T> {

    public QueryResult<T> pageToResult(IPage<T> page) {
        QueryResult<T> result = new QueryResult<T>();
        result.setResultData(page.getRecords());
        result.setTotalRecord(page.getTotal());
        return result;
    }

}