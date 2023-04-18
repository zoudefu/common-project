package com.common.project.dataanalysis.entity;


import com.common.project.dataanalysis.handler.Handler;

import lombok.Data;

@Data
public class DataAnalysisHandler {
    /**
     * 客户端处理
     */
    public  Handler<DataSoureConfig>  clientHandler;
    /**
     * 查询处理
     */
    public  Handler<DataSoureConfig>  queryHandler;
    /**
     * 输出处理
     */
    public  Handler<DataSoureConfig>  outputHandler;

}
