package com.wyc.mqtt.service.config;

import com.wyc.mqtt.service.entity.EmqApiInformationProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wangyuanchen
 * @date: 2019-11-7 10:43
 * @description:
 */
@Configuration
@EnableConfigurationProperties
@ConditionalOnProperty( value = "emq.httpapiurl.enable")
public class CustomConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "emq.httpapiurl")
    public EmqApiInformationProperty authorizationProperty() {
        return new EmqApiInformationProperty();
    }

}
