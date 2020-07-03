package com.wyc.mqtt.service.interceptor;

import com.wyc.mqtt.service.entity.EmqApiInformationProperty;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;

/**
 * @author: wangyuanchen
 * @date: 2019-12-3 11:33
 * @description:
 */
public class HeadClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(HeadClientHttpRequestInterceptor.class);

    @Autowired
    private EmqApiInformationProperty emqApiInformationProperty;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        log.info("#####head handle########");
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add("Accept", "application/json");
        headers.add("Accept-Encoding", "gzip");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        String auth = emqApiInformationProperty.getAppId() + ":" + emqApiInformationProperty.getAppSecret();
     /*   String encodedAuth = Base64.encode(
                auth.getBytes(StandardCharsets.UTF_8));*/
        String authHeader = "Basic " + Base64.encode(auth.getBytes());
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
        HttpHeaders headersResponse = response.getHeaders();
        headersResponse.add("Accept", "application/json");
        return  response;
    }
}
