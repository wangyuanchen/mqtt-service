package com.wyc.mqtt.service.entity;

import lombok.Data;

/**
 * @author: wangyuanchen
 * @date: 2019-11-20 16:05
 * @description:
 */
@Data
public class MqttRes {
    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MESSAGE = "处理成功";
    private String code;
    private String message;
    private String datagram;
}
