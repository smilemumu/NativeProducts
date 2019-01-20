package com.shibro.nativeproducts.data.vo;


import com.shibro.nativeproducts.data.enums.ErrorCodeEnum;

/**
 * 此类描述的是：请求返回的公用数据
 *
 * @author xujinfei
 */

public class BaseResponseVo {
    /**
     * 成功标记
     */
    private boolean success = true;

    /**
     * 错误码
     */
    private int code = ErrorCodeEnum.SUCCESS.getCode();

    /**
     * 错误信息
     */
    private String msg = "";

    /**
     * 返回的具体数据
     */
    private Object data;

    public BaseResponseVo() {
    }

    public BaseResponseVo(ErrorCodeEnum errorCodeEnum) {
        setErrorCodeEnum(errorCodeEnum);
    }

    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        this.success = false;
        if (null == errorCodeEnum) {
            return;
        }

        if(errorCodeEnum == ErrorCodeEnum.SUCCESS) {
            this.success = true;
        }

        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getDesc();
    }


    public static BaseResponseVo failResponseVo() {
        BaseResponseVo responseVo = new BaseResponseVo();
        responseVo.setSuccess(false);
        responseVo.setCode(ErrorCodeEnum.UNKNOWN_ERROR.getCode());
        responseVo.setMsg(ErrorCodeEnum.UNKNOWN_ERROR.getDesc());
        return responseVo;
    }

    public static BaseResponseVo successResponseVo(Object data) {
        BaseResponseVo responseVo = new BaseResponseVo();
        responseVo.setSuccess(true);
        responseVo.setCode(ErrorCodeEnum.SUCCESS.getCode());
        responseVo.setMsg(ErrorCodeEnum.SUCCESS.getDesc());
        responseVo.setData(data);
        return responseVo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
