package com.shibro.nativeproducts.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class WebLogAspect {
    private static final Logger LOG = LoggerFactory.getLogger(WebLogAspect.class);


    @Pointcut("execution(* com.shibro.nativeproducts.controller..*Controller.*(..))")
    public void pointService(){

    }

}
