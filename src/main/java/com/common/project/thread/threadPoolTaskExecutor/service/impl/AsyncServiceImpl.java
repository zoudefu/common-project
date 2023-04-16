package com.common.project.thread.threadPoolTaskExecutor.service.impl;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.common.project.thread.threadPoolTaskExecutor.entity.LogOutputResult;
import com.common.project.thread.threadPoolTaskExecutor.service.AsyncService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(List <LogOutputResult> logOutputResults, CountDownLatch countDownLatch) {
        try{
            log.warn("start executeAsync");
            //异步线程要做的事情
            //dosometing ............................
            log.warn("end executeAsync");
        }finally {
            countDownLatch.countDown();// 很关键, 无论上面程序是否异常必须执行countDown,否则await无法释放
        }
    }
}