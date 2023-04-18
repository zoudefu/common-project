package com.common.project.dataanalysis.service;
import java.util.Map;
import com.common.project.dataanalysis.entity.DataSoureConfig;
public interface IDataAnalysisService {

    

    /**
     * 解析数据落地文件 
     */
    public String  landingDocuments( DataSoureConfig dataSoureConfig);
    /**
     * 检测连通性
     *
     */
    public String checkClient(DataSoureConfig dataSoureConfig);
    /**
     * 分页查询数据
     *
     */
    public Map<String, Object> paginationQuery( DataSoureConfig dataSoureConfig);
}
