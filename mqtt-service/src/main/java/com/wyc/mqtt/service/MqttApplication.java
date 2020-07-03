package com.wyc.mqtt.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: wangyuanchen
 * @date: 2019-11-20 15:37
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.wyc.mqtt.service.feign")
public class MqttApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqttApplication.class,args);
    }
}
