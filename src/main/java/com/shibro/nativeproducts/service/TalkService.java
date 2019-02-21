package com.shibro.nativeproducts.service;

import com.shibro.nativeproducts.data.enums.TalkWhatEnum;
import com.shibro.nativeproducts.data.vo.BaseResponseVo;
import com.shibro.nativeproducts.data.vo.requestVo.TalkWhatRequestVo;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseData;
import com.shibro.nativeproducts.utils.TalkServiceFactory;
import org.springframework.stereotype.Service;

@Service
public class TalkService {

    public BaseResponseVo getTalk(TalkWhatRequestVo requestVo) {
        TalkWhatEnum talkWhatEnum  = queryTalkWhatEnum(requestVo);
        BaseTalkResponseData responseData = TalkServiceFactory.getBean(talkWhatEnum).getTalk();
        return BaseResponseVo.successResponseVo(responseData);
    }

    private TalkWhatEnum queryTalkWhatEnum(TalkWhatRequestVo requestVo) {
        String key = requestVo.getKey();
        TalkWhatEnum result;
        switch (key){
            case "hello":{
                result = TalkWhatEnum.TALKHELLO;
                break;
            }
            case "yes":{
                result = TalkWhatEnum.TALKYES;
                break;
            }
            default:{
                result = TalkWhatEnum.TALKHELLO;
                break;
            }
        }
      return result;
    }
}
