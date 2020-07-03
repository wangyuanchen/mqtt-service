package com.wyc.mqtt.service.enums;

/**
 * MQTT服务错误码
 *  @author wangyuanchen
 */
public enum MqttErrorCode {
    // 发送失败
    FAILED("04000", "发送失败"),
    // 客户端秘钥为空
    CLIENT_KEY_ISNULL("04001", "客户端秘钥为空"),
    // CONTENT报文为空
    CONTENT_ISNULL("04002", "CONTENT报文为空"),
    // 参数错误
    PARAM_FAILURE("04003", "参数错误"),
    // 参数类型错误
    PARAM_TYPE_FAILURE("04004", "参数类型错误"),
    // 客户端已离线
    CLIENT_IS_OFFLINE("04005", "客户端已离线"),
    // 对象为空
    OBJECT_ISNULL("04006", "对象为空"),
    // 系统错误,
    SYSTEM_ERROR("04500", "系统错误，请稍后重试"),
    // 超时错误
    TIMEOUT_FAILURE("04998", "MQTT内部连接超时")
    ;

    private String code;
    private String message;

    MqttErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
