package com.shibro.nativeproducts.data.exception;

import com.shibro.nativeproducts.data.enums.ErrorCodeEnum;

public class BaseException extends Exception {

    private int code;

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BaseException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDesc());
        this.code = errorCodeEnum.getCode();
    }

    public BaseException(ErrorCodeEnum errorCode, String message) {
        super(errorCode.getCode() + ":" + message);
        this.setCode(errorCode.getCode());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
