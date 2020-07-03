package com.wyc.mqtt.service.utils;

import com.wyc.mqtt.service.entity.MqttRes;
import com.wyc.mqtt.service.enums.MqttErrorCode;
import com.wyc.mqtt.service.enums.MqttSuccessCode;

/**
 * @author: wangyuanchen
 * @date: 2019-11-27 11:07
 * @description:mqtt服务信息返回工具类
 */
public class ReturnMessageUtil {

    /**
     * 空字符串
     */
    private final static String NULL_STR = "";

    public static MqttRes setReturnSuccessMessage(){
        return setReturnMessage(MqttSuccessCode.SUCCESS.getCode(),MqttSuccessCode.SUCCESS.getMessage());
    }

    public static MqttRes setReturnFailedMessage(){
        return setReturnMessage(MqttErrorCode.FAILED.getCode(),MqttErrorCode.FAILED.getMessage());
    }

    public static MqttRes setReturnMessage(String code){
        for (MqttErrorCode mqttErrorCode:MqttErrorCode.values())
            if (code.equals(mqttErrorCode.getCode()))
                return setReturnMessage(code,mqttErrorCode.getMessage());
        return null;
    }

    public static MqttRes setReturnMessage(String code,String message){
        return setReturnMessage(code,message,NULL_STR);
    }

    public static MqttRes setReturnMessage(String code,String message,String datagram){
        MqttRes mqttRes = new MqttRes();
        mqttRes.setCode(code);
        mqttRes.setMessage(message);
        mqttRes.setDatagram(datagram);
        return mqttRes;
    }
}
