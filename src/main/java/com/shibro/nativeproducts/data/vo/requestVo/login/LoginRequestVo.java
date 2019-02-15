package com.shibro.nativeproducts.data.vo.requestVo.login;

import com.shibro.nativeproducts.data.vo.BaseRequestVo;
import lombok.Data;

@Data
public class LoginRequestVo extends BaseRequestVo {
    private String userName;
    private String password;

}
