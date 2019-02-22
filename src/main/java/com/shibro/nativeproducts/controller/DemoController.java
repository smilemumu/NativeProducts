package com.shibro.nativeproducts.controller;

import com.shibro.nativeproducts.annotation.DemoAnnotation;
import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.data.vo.requestVo.RedisSetParams;
import com.shibro.nativeproducts.data.vo.requestVo.TalkWhatRequestVo;
import com.shibro.nativeproducts.service.DemoService;
import com.shibro.nativeproducts.service.TalkService;
import com.shibro.nativeproducts.utils.redis.JedisTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {

    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private TalkService talkService;
    @Resource
    private JedisTemplate jedisTemplate;
    @Resource
    private DemoService demoService;


    @DemoAnnotation(name = "demoMethod")
    @RequestMapping(value = "/demo", method = RequestMethod.POST)
    public String demoMethod(@RequestBody BaseRequestVo requestVo){
        LOG.info("demoMethod");
        return "1";
    }

    /**
     * ApplicationContextAware
     */
    @DemoAnnotation(name = "getTalk")
    @RequestMapping(value = "/getTalk", method = RequestMethod.POST)
    public BaseResponseVo getTalk(@RequestBody TalkWhatRequestVo requestVo){
        LOG.info("getTalk");
        return talkService.getTalk(requestVo);
    }

    /**
     * redis相关
     */
    @RequestMapping(value = "/redis/get", method = {RequestMethod.GET})
    public String getKey(@RequestBody String key){
        return jedisTemplate.get(key);
    }
    @RequestMapping(value = "/redis/del", method = {RequestMethod.GET})
    public void delKey(@RequestBody String key){
        jedisTemplate.del(key);
    }
    @RequestMapping(value = "/redis/set", method = {RequestMethod.POST})
    public void delKey(@RequestBody RedisSetParams params){
        jedisTemplate.set(params.getKey(),params.getValue());
    }

    /**
     * 线程池相关
     */
    @RequestMapping(value = "/testTask", method = {RequestMethod.POST})
    public void testTask(@RequestBody BaseRequestVo requestVo){
        demoService.testTask();
    }
}
