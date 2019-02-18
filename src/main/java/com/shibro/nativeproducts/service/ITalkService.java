package com.shibro.nativeproducts.service;


import com.shibro.nativeproducts.data.enums.TalkWhatEnum;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseData;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseDetail;

public interface ITalkService {
    BaseTalkResponseData<? extends BaseTalkResponseDetail> getTalk();

    TalkWhatEnum getTalkWhatEnum();
}
