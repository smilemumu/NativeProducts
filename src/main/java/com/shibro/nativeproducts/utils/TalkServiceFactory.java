package com.shibro.nativeproducts.utils;

import com.shibro.nativeproducts.data.enums.TalkWhatEnum;
import com.shibro.nativeproducts.service.ITalkService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TalkServiceFactory implements ApplicationContextAware {

    private static Map<TalkWhatEnum, ITalkService> talkServiceMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ITalkService> map = applicationContext.getBeansOfType(ITalkService.class);
        talkServiceMap = new HashMap<>();
        map.forEach((key,value)->{
            talkServiceMap.put(value.getTalkWhatEnum(),value);
        });
    }

    public <T extends ITalkService> T getBean(TalkWhatEnum talkWhatEnum){
        return (T)talkServiceMap.get(talkWhatEnum);
    }
}
