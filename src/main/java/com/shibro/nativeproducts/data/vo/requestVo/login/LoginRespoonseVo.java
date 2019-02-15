package com.shibro.nativeproducts.data.vo.requestVo.login;

import lombok.Data;

@Data
public class LoginRespoonseVo {
    private String token;

    public LoginRespoonseVo(String token) {
        this.token = token;
    }

    public LoginRespoonseVo() {
    }
}
