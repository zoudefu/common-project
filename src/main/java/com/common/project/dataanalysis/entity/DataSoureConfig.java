package com.common.project.dataanalysis.entity;

import lombok.Data;

@Data
public class DataSoureConfig {
    /**
     * 引擎类型
     */
    public String  engineType;
     /**
     * 链接配置
     */
    public ClientConfig clientConfig;
     /**
     * 查询配置
     */
    public QueryConfig queryConfig;
     /**
     * 输出配置
     */
    public OutPutConfig outPutConfig;
    
}
