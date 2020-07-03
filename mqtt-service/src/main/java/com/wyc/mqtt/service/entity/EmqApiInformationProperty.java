package com.wyc.mqtt.service.entity;

import lombok.Data;

/**
 * @author: wangyuanchen
 * @date: 2019-12-3 10:43
 * @description:
 */
@Data
public class EmqApiInformationProperty {
    /**
     * 是否开启配置文件中注解
     */
    private Boolean enable;
    /**
     * 基本路径
     */
    private String baseUrl;
    /**
     *id
     */
    private String appId;
    /**
     *密码
     */
    private String appSecret;
    /**
     * List available clientid in the cluster
     */
    private String getSessionListUrl;
    /**
     * Lookup clientid in the cluster
     */
    private String getSessionIdUrl;
    /**
     * A list of connections in the cluster
     */
    private String getConnectionListUrl;
    /**
     * Lookup a connection in the cluster
     */
    private String getConnectionIdUrl;


}
