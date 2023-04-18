
package com.common.project.dataanalysis.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.common.project.dataanalysis.entity.DataAnalysisHandler;
import com.common.project.dataanalysis.entity.DataSoureConfig;
import com.common.project.dataanalysis.enums.ServiceTypeEnum;
import com.common.project.dataanalysis.handler.Handler;
import com.common.project.dataanalysis.handler.Handler.Builder;
import com.common.project.dataanalysis.service.IDataAnalysisService;
import com.common.project.dataanalysis.util.SpringContextHolder;
import com.common.project.generator.base.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataAanlysisService implements IDataAnalysisService {

    public DataAnalysisHandler createDataAnalysisHandler(DataSoureConfig dataSoureConfig) {
        DataAnalysisHandler dataAnalysisHandler = new DataAnalysisHandler();
        ServiceTypeEnum typeEnum = null;
        typeEnum = ServiceTypeEnum.getByValue(dataSoureConfig.getEngineType());
        if (typeEnum == null) {
            throw new BusinessException(-1, "当前类型数据引擎不支持");
        }
        String clientHandlerName = typeEnum.getClientHandlerName();
        String queryHandlerName = typeEnum.getQueryHandlerName();
        String outpuHandlerName = typeEnum.getOutPutHandlerName();
        Handler<DataSoureConfig> clientHandler = null;
        try {
            clientHandler = SpringContextHolder.getBean(queryHandlerName);
        } catch (Exception e) {
            throw new BusinessException(-1, "未找到客户端处理类");
        }
        Handler<DataSoureConfig> queryHandler = null;
        try {
            queryHandler = SpringContextHolder.getBean(clientHandlerName);
        } catch (Exception e) {
            throw new BusinessException(-1, "未找到查询处理类");
        }
        Handler<DataSoureConfig> outputHandler = null;
        try {
            outputHandler = SpringContextHolder.getBean(outpuHandlerName);
        } catch (Exception e) {
            throw new BusinessException(-1, "未找到输出处理类");
        }
        dataAnalysisHandler.setClientHandler(clientHandler);
        dataAnalysisHandler.setQueryHandler(queryHandler);
        dataAnalysisHandler.setOutputHandler(outputHandler);
        return dataAnalysisHandler;
    }

    @Override
    public String landingDocuments(DataSoureConfig dataSoureConfig) {
        log.info("数据解析开始");
      DataAnalysisHandler dataAnalysisHandler=createDataAnalysisHandler(dataSoureConfig);
      Builder<DataSoureConfig> builder = new Handler.Builder<DataSoureConfig>();
      builder.addHandler(dataAnalysisHandler.getClientHandler())
          .addHandler(dataAnalysisHandler.getQueryHandler())
          .addHandler(dataAnalysisHandler.getOutputHandler());
      builder.build().doHandler(dataSoureConfig);
      log.info("数据解析结束");
        return null;
    }

    @Override
    public String checkClient(DataSoureConfig dataSoureConfig) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> paginationQuery(DataSoureConfig dataSoureConfig) {
        // TODO Auto-generated method stub
        return null;
    }

}
