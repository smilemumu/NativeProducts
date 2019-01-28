package com.shibro.nativeproducts.data.vo.requestvo.login;

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
