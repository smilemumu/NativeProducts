package com.shibro.nativeproducts.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemoService {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private ThreadPoolTaskExecutor baseTaskExecutor;

    public void testTask() {
        baseTaskExecutor.execute(()->{

        });
        threadPoolTaskExecutor.execute(()->{

        });
    }
}
