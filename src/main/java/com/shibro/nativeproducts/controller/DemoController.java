package com.shibro.nativeproducts.controller;

import com.shibro.nativeproducts.annotation.DemoAnnotation;
import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseData;
import com.shibro.nativeproducts.service.ITalkService;
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
    private ITalkService talkService;

    @DemoAnnotation(name = "demoMethod")
    @RequestMapping(value = "/demo", method = RequestMethod.POST)
    public String demoMethod(@RequestBody BaseRequestVo requestVo){
        LOG.info("demoMethod");
        return "1";
    }

    @DemoAnnotation(name = "getTalk")
    @RequestMapping(value = "/getTalk", method = RequestMethod.POST)
    public BaseTalkResponseData getTalk(@RequestBody BaseRequestVo requestVo){
        LOG.info("getTalk");
        return talkService.getTalk();
    }
}
