package com.shibro.nativeproducts.aspect;

import com.alibaba.fastjson.JSON;
import com.shibro.nativeproducts.annotation.DemoAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DemoAspect {
    private static final Logger LOG = LoggerFactory.getLogger(DemoAspect.class);

    // 声明切点
    @Pointcut("execution(* com.shibro.nativeproducts..*(..))")
    public void pointcut() {
    }

    @Around("pointcut() && @annotation(anno)")
    public Object around(ProceedingJoinPoint pjp, DemoAnnotation anno) throws Throwable {
        Object result = null;
        try {
            //切面之前
            LOG.info("demoAspect before");
            result = pjp.proceed();
            //切面之后
            LOG.info("demoAspect after");
        } catch (Throwable e) {
            LOG.error("PersonAnalysisAspect 处理异常", e);
            throw e;
        } finally {
           //最后干嘛
            LOG.info("demoAspect finally");
            finallyDo(pjp,anno,result);
        }
        return result;
    }

    private void finallyDo(ProceedingJoinPoint pjp,DemoAnnotation anno,Object result){
        LOG.info("pjp，{}", pjp);
        LOG.info("anno，{}",anno);
        LOG.info("result，{}",JSON.toJSONString(result));
    }
}
