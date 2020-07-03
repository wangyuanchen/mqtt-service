package com.wyc.mqtt.service.controller;

import com.wyc.common.utils.TriPleDesUtil;
import com.wyc.mqtt.service.annotation.NeedClientKey;
import com.wyc.mqtt.service.entity.EmqApiInformationProperty;
import com.wyc.mqtt.service.entity.MqttInternalProducer;
import com.wyc.mqtt.service.entity.MqttReq;
import com.wyc.mqtt.service.entity.MqttRes;
import com.wyc.mqtt.service.enums.MqttErrorCode;
import com.wyc.mqtt.service.security.ClientKeyContext;
import com.wyc.mqtt.service.service.IMqttSender;
import com.wyc.mqtt.service.utils.ReturnMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * MQTT消息发送
 *
 * @author wangyuanchen
 */
@RestController
@Validated
public class MqttController {

    private static final Logger log = LoggerFactory.getLogger(MqttController.class);
    /**
     * 注入发送MQTT的Bean
     */
    @Resource
    private IMqttSender iMqttSender;
    /**
     * 客户端秘钥
     */
    private String clientSecretKey;

    @Autowired
    private EmqApiInformationProperty emqApiInformationProperty;

    @Autowired
    @Qualifier("basicAuthRestTemplate")
    private RestTemplate restTemplate;

    /**
     * 发送MQTT消息
     * @param message 消息内容
     * @return 返回
     */
    @RequestMapping(value = "/mqtt", method = RequestMethod.POST)
    public ResponseEntity<String> sendMqtt(@RequestParam(value = "msg") String message) {
        iMqttSender.sendToMqtt(message);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * 发送MQTT消息
     * @param mqttJson 消息内容
     * @return 返回
     */
    @RequestMapping(value = "/mqtttopic", method = RequestMethod.POST)
    @NeedClientKey
    public MqttRes sendMqttTopic(@RequestBody @Validated MqttReq mqttJson) {
        // 1、获取并验证客户端秘钥
        clientSecretKey = ClientKeyContext.getClientKey();

        // 2、调用HTTPAPI查看客户端id是否在线
        Map<String,String> param = new HashMap<>(8);
        param.put("clientid","serverMqttConsumer");
        Map responseContent = restTemplate.getForObject(emqApiInformationProperty.getBaseUrl()+emqApiInformationProperty.getGetConnectionIdUrl(),Map.class ,param);
        Optional<Integer> count = Stream.of(Optional.ofNullable(responseContent).orElseThrow(() -> new IllegalArgumentException())).filter(u -> !"0".equals(u.get("code"))).map(u -> (List) u.get("data")).map(c -> c.size()).findFirst();
        if (count.isPresent()&&count.get()==0){
            return ReturnMessageUtil.setReturnMessage(MqttErrorCode.CLIENT_IS_OFFLINE.getCode(),MqttErrorCode.CLIENT_IS_OFFLINE.getMessage());
        }
        // 3.组装topic进行发送
        String topic = mqttJson.getTaxpayerId()+"/"+mqttJson.getMachineNo()+"/0";

        MqttInternalProducer mqttInternalProducer = new MqttInternalProducer();
        mqttInternalProducer.setType(mqttJson.getType());
        mqttInternalProducer.setDatagram(TriPleDesUtil.encode3Des(mqttJson.getDatagram(),clientSecretKey));

        iMqttSender.sendToMqtt(topic,mqttInternalProducer.toCustomizedString());
        return ReturnMessageUtil.setReturnSuccessMessage();
    }
}