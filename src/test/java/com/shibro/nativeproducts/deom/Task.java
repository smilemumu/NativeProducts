package com.shibro.nativeproducts.deom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.Future;

@Component
public class Task {

    public static Random random =new Random();
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private ThreadPoolTaskExecutor baseTaskExecutor;
    private static final Logger LOG = LoggerFactory.getLogger(Task.class);

    @Async("threadPoolTaskExecutor")
    public Future<String> doTaskOne() throws Exception {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }
    @Async("threadPoolTaskExecutor")
    public Future<String> doTaskTwo() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }

    @Async("threadPoolTaskExecutor")
    public Future<String> doTaskThree() throws Exception {
        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }

    public String doTaskFour() throws Exception {
        System.out.println("开始做任务4");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务4，耗时：" + (end - start) + "毫秒");
        return "任务4完成";
    }

    public void test() throws Exception {
        long start = System.currentTimeMillis();
        Task task = new Task();
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();
        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();
        LOG.info(task1.get());
        LOG.info(task2.get());
        LOG.info(task3.get());
        System.out.println("test()任务全部完成，总耗时：" + (end - start) + "毫秒");
    }
}
