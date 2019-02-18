package com.shibro.nativeproducts.service;

import com.shibro.nativeproducts.data.enums.TalkWhatEnum;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.data.vo.requestVo.TalkWhatRequestVo;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseData;
import com.shibro.nativeproducts.utils.TalkServiceFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TalkService {

    @Resource
    private TalkServiceFactory talkServiceFactory;
    
    public BaseResponseVo getTalk(TalkWhatRequestVo requestVo) {
        TalkWhatEnum talkWhatEnum  = convertTalkWhatEnum(requestVo);
        BaseTalkResponseData responseData = talkServiceFactory.getBean(talkWhatEnum).getTalk();
        return BaseResponseVo.successResponseVo(responseData);
    }

    private TalkWhatEnum convertTalkWhatEnum(TalkWhatRequestVo requestVo) {
        if("hello".equals(requestVo.getKey())){
            return  TalkWhatEnum.TALKHELLO;
        }else if("yes".equals(requestVo.getKey())){
            return  TalkWhatEnum.TALKYES;
        }else {
            return TalkWhatEnum.TALKHELLO;
        }
    }
}
