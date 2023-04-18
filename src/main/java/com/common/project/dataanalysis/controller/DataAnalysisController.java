package com.common.project.dataanalysis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.project.dataanalysis.entity.DataSoureConfig;
import com.common.project.dataanalysis.service.IDataAnalysisService;
import com.common.project.generator.base.Response;
import com.common.project.generator.base.ResponseCallBack;
import com.common.project.generator.base.ResponseCriteria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "数据解析")
@RestController
@RequestMapping("/dataAnalysis")
public class DataAnalysisController {
    
    @Autowired
    private  IDataAnalysisService dataAnalysisService;

    /**
     * 解析数据落地文件 
     *
     * @param dataSoureConfig
     * @return
     */
    @ApiOperation(value = "解析数据落地文件", notes = "针对不同数据源解析落地文件")
    @PostMapping("/landingDocuments")
    public Response landingDocuments(@RequestBody DataSoureConfig dataSoureConfig) {
        return new ResponseCallBack() {
            @Override
            public void execute(ResponseCriteria criteria, Object... objects) {
                criteria.addSingleResult(dataAnalysisService.landingDocuments(dataSoureConfig));
            }
        }.sendRequest();
    }
    
    /**
     * 检测连通性
     *
     * @param dataSoureConfig
     * @return
     */
    @ApiOperation(value = "检测连通性", notes = "检测数据源是否能够连通")
    @PostMapping("/checkClient")
    public Response checkClient(@RequestBody DataSoureConfig dataSoureConfig) {
        return new ResponseCallBack() {
            @Override
            public void execute(ResponseCriteria criteria, Object... objects) {
                criteria.addSingleResult(dataAnalysisService.checkClient(dataSoureConfig));
            }
        }.sendRequest();
    }
    
    
    /**
     *分页查询数据
     *
     * @param dataSoureConfig
     * @return
     */
    @ApiOperation(value = "分页查询数据", notes = "分页查询数据")
    @PostMapping("/paginationQuery")
    public Response paginationQuery(@RequestBody DataSoureConfig dataSoureConfig) {
        return new ResponseCallBack() {
            @Override
            public void execute(ResponseCriteria criteria, Object... objects) {
                criteria.addSingleResult(dataAnalysisService.paginationQuery(dataSoureConfig));
            }
        }.sendRequest();
    }
    
    
}
