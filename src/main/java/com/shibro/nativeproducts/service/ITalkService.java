package com.shibro.nativeproducts.service;


import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseData;
import com.shibro.nativeproducts.data.vo.responseVo.BaseTalkResponseDetail;
import org.springframework.stereotype.Service;

public interface ITalkService {
    BaseTalkResponseData<? extends BaseTalkResponseDetail> getTalk();

}
