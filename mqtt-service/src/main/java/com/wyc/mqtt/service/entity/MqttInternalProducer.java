package com.wyc.mqtt.service.entity;

import lombok.Data;

/**
 * @author: wangyuanchen
 * @date: 2019-12-9 15:09
 * @description:
 */
@Data
public class MqttInternalProducer {

    /**
     * 0:注册文件 1:业务数据
     */
    private String type;
    /**
     * 报文
     */
    private String datagram;

    public String toCustomizedString(){
        return "{" +
                "\"type\":\"" + type + "\"," +
                "\"datagram\":\"" + datagram + "\"" +
                "}";
    }
}
