package com.common.project.elasticsearch.handler;


import com.common.project.dataanalysis.entity.DataSoureConfig;
import com.common.project.dataanalysis.handler.Handler;

public class EsOutputHandler  extends Handler<DataSoureConfig> {

    @Override
    public void doHandler(DataSoureConfig dataSoureConfig) {
        // TODO Auto-generated method stub
        next.doHandler(dataSoureConfig );
    }

}
