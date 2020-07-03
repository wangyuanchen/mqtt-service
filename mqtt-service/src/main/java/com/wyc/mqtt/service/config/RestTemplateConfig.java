package com.wyc.mqtt.service.config;

import com.wyc.mqtt.service.interceptor.HeadClientHttpRequestInterceptor;
import com.wyc.mqtt.service.interceptor.TrackLogClientHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

/**
 * RestTemplate配置模板
 *
 * @author wangyuanchen
 */
@Configuration
public class RestTemplateConfig {

    @Value("${resttemplate.connection.timeout}")
    private int restTemplateConnectionTimeout;
    @Value("${resttemplate.read.timeout}")
    private int restTemplateReadTimeout;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        //配置自定义的interceptor拦截器
        List<ClientHttpRequestInterceptor> interceptors=new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new TrackLogClientHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory reqFactory= new SimpleClientHttpRequestFactory();
        reqFactory.setConnectTimeout(restTemplateConnectionTimeout);
        reqFactory.setReadTimeout(restTemplateReadTimeout);
        return reqFactory;
    }

    @Bean
    public HeadClientHttpRequestInterceptor headClientHttpRequestInterceptor(){return new HeadClientHttpRequestInterceptor();}

    @Bean("basicAuthRestTemplate")
    public RestTemplate createBasicAuthRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        //配置自定义的interceptor拦截器
        List<ClientHttpRequestInterceptor> interceptors=new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(headClientHttpRequestInterceptor());
        interceptors.add(new TrackLogClientHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
