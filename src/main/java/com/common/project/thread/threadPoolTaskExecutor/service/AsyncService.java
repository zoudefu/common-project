package com.common.project.thread.threadPoolTaskExecutor.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.common.project.thread.threadPoolTaskExecutor.entity.LogOutputResult;

public interface AsyncService {
    
    public void executeAsync(List <LogOutputResult> logOutputResults, CountDownLatch countDownLatch);

}
