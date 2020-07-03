package com.wyc.mqtt.service.enums;

/**
 * MQTT服务成功码
 *  @author wangyuanchen
 */
public enum MqttSuccessCode {
    // 操作成功
    SUCCESS("0","发送成功"),
    ;

    private String code;
    private String message;

    MqttSuccessCode(String code, String message) {
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
