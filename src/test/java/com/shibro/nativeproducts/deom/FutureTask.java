package com.shibro.nativeproducts.deom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Callable;

@Component
public class FutureTask implements Callable<String>, Serializable {

    public static Random random =new Random();

    private String param;

    public void setParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    private static final Logger LOG = LoggerFactory.getLogger(FutureTask.class);

    public String doFutureTask(String param) throws Exception {
        LOG.info("开始做doFutureTask");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        LOG.info("doFutureTask，耗时：" + (end - start) + "毫秒");
        return "doFutureTask"+param;
    }


    @Override
    public String call() throws Exception {
        LOG.info(Thread.currentThread().getName() + "....");
        return doFutureTask(this.param);
    }
}
