package com.shibro.nativeproducts.data.enums;

public enum ErrorCodeEnum {
    SUCCESS(70000,"成功"),
    UNKNOWN_ERROR(70001,"未知异常，请稍后再试"),
    SYSTEM_ERROR(70002,"系统异常，请稍后再试"),
    PARAMS_ERROR(70003,"参数异常"),
    RESULT_IS_NULL(70004,"返回数据为空"),
    LOGIN_FAIL(70005,"登录失败，请输入正确的用户名密码"),
    LOGIN_REPAET(70006,"登录失败，请不要重复登录"),
    NEED_LOGIN(70007,"未登录"),
    REGISTER_REPEAT_NAME(70008,"该用户名已被注册，请重新输入"),
    REPEAT_REQUEST(80000,"重复请求")
    ;
    private Integer code;
    private String desc;

    ErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
