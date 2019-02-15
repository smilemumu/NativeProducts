package com.shibro.nativeproducts.service.impl;

import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseData;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseDetail;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkYesResponseDetail;
import com.shibro.nativeproducts.service.ITalkService;
import org.springframework.stereotype.Service;

@Service
public class TalkYesService implements ITalkService {
    @Override
    public BaseTalkResponseData<? extends BaseTalkResponseDetail> getTalk() {
        BaseTalkYesResponseDetail talkYesResponseDetail = new BaseTalkYesResponseDetail();
        talkYesResponseDetail.setSayYes("say yes");
        BaseTalkResponseData<BaseTalkYesResponseDetail> talkResponseData = new BaseTalkResponseData<>();
        talkResponseData.setDetails(talkYesResponseDetail);
        talkResponseData.setName("sayYes");
        return talkResponseData;
    }
}
