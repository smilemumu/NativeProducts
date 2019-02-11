package com.shibro.nativeproducts.aspect;

import com.alibaba.fastjson.JSON;
import com.shibro.nativeproducts.data.enums.ErrorCodeEnum;
import com.shibro.nativeproducts.data.exception.BaseException;
import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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
    @Around("pointService()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        int index = 0;
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            Object object = joinPoint.getArgs()[i];
            if (object instanceof BaseRequestVo) {
                index = i;
                break;
            }
        }
        String methodName = joinPoint.getSignature().getName();
        LOG.info("方法名 {} ,入参 {}", methodName, JSON.toJSONString(joinPoint.getArgs()[index]));
        Object obj;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            LOG.error("执行出错", throwable);
            BaseResponseVo responseVo;
            if (BaseException.class.isAssignableFrom(throwable.getClass())) {
                if (throwable instanceof BaseException) {
                    responseVo = BaseResponseVo.failResponseVo(((BaseException)throwable).getCode(), throwable.getMessage());
                } else {
                    responseVo = new BaseResponseVo(ErrorCodeEnum.SYSTEM_ERROR);
                }
            } else {
                responseVo = new BaseResponseVo(ErrorCodeEnum.SYSTEM_ERROR);
            }
            return responseVo;
        }
        long costTime = System.currentTimeMillis() - start;
        LOG.info("方法名 {} ,出参 {}, 耗时 {}", methodName, JSON.toJSONString(obj), costTime);
        return obj;
    }


}
