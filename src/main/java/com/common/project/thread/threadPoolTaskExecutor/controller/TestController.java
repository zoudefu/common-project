package com.common.project.thread.threadPoolTaskExecutor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.project.thread.threadPoolTaskExecutor.entity.LogOutputResult;
import com.common.project.thread.threadPoolTaskExecutor.service.impl.AsyncServiceImpl;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Component
@Api(tags="异步并发插入百万接口ThreadPoolTaskExecutor")
@Slf4j
public class TestController {
    @Autowired
    public   AsyncServiceImpl asyncService;

    public int testMultiThread() {
        List<LogOutputResult> logOutputResults = new ArrayList<>();
        //测试每100条数据插入开一个线程
        //List<List<LogOutputResult>> lists = ConvertHandler.splitList(logOutputResults, 100);
        List<List<LogOutputResult>> lists = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(logOutputResults.size());
        for (List<LogOutputResult> listSub:lists) {
            asyncService.executeAsync(listSub,countDownLatch);
        }
        try {
            countDownLatch.await(); //保证之前的所有的线程都执行完成，才会走下面的；
            // 这样就可以在下面拿到所有线程执行完的集合结果
        } catch (Exception e) {
            log.error("阻塞异常:"+e.getMessage());
        }
        return logOutputResults.size();
    }
}
