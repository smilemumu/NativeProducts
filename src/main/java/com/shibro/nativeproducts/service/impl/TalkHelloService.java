package com.shibro.nativeproducts.service.impl;

import com.shibro.nativeproducts.data.enums.TalkWhatEnum;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkHelloResponseDetail;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseData;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseDetail;
import com.shibro.nativeproducts.service.ITalkService;
import org.springframework.stereotype.Service;

@Service
public class TalkHelloService implements ITalkService {

    @Override
    public BaseTalkResponseData<? extends BaseTalkResponseDetail> getTalk() {
        BaseTalkHelloResponseDetail talkHelloResponseDetail = new BaseTalkHelloResponseDetail();
        talkHelloResponseDetail.setSayHello("say hello");
        BaseTalkResponseData<BaseTalkHelloResponseDetail> talkResponseData = new BaseTalkResponseData<>();
        talkResponseData.setDetails(talkHelloResponseDetail);
        talkResponseData.setName("sayHello");
        return talkResponseData;
    }

    @Override
    public TalkWhatEnum getTalkWhatEnum() {
        return TalkWhatEnum.TALKHELLO;
    }
}
