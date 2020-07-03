package com.wyc.common.enumtype;

/**
 * 状态码 枚举类
 *  @author LiuMi
 *  @date 2019年9月11日
 */
public enum ResultCodeEnum {

    SUCCESS("200","请求成功"),
    POST_SUCCESS("201", "操作成功"),
    ERROR("400","请求失败"),
    SYSTEM_ERROR("500", "系统异常"),
    BUSSINESS_ERROR("501", "业务逻辑错误"),
    VERIFY_CODE_ERROR("502", "业务参数错误"),
    PARAM_ERROR("503", "业务参数错误"),
    MQTT_SERVICE_TIMEOUT("04999", "调用mqtt服务超时"),
    ;

    private String code;
    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
