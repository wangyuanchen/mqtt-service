package com.wyc.mqtt.service.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author: wangyuanchen
 * @date: 2019-12-3 11:30
 * @description:
 */
public class TrackLogClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger log = LoggerFactory.getLogger(TrackLogClientHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        trackRequest(request,body);
        ClientHttpResponse httpResponse = execution.execute(request, body);
        trackResponse(httpResponse);
        return httpResponse;
    }

    private void trackResponse(ClientHttpResponse httpResponse)throws IOException {
        log.info("============================response begin==========================================");
        log.info("Status code  : {}", httpResponse.getStatusCode());
        log.info("Status text  : {}", httpResponse.getStatusText());
        log.info("Headers      : {}", httpResponse.getHeaders());
        log.info("=======================response end=================================================");
    }

    private void trackRequest(HttpRequest request, byte[] body)throws UnsupportedEncodingException {
        log.info("======= request begin ========");
        log.info("uri : {}", request.getURI());
        log.info("method : {}", request.getMethod());
        log.info("headers : {}", request.getHeaders());
        log.info("request body : {}", new String(body, "UTF-8"));
        log.info("======= request end ========");
    }
}
