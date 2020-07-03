package com.wyc.mqtt.service.feign;

import com.wyc.mqtt.service.config.FeignConfig;
import com.wyc.mqtt.service.feign.fallback.ClientServiceInterfaceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author wangyuanchen
 * @version V1.0
 * @Package com.aisino.social.common.feign
 * @Description: (客户服务声明式调用)
 * @date 2019/9/17 -   14:09
 */
@FeignClient(name = "client-service", configuration = FeignConfig.class, fallbackFactory = ClientServiceInterfaceFallbackFactory.class)
public interface ClientServiceInterface {

    /**
    * @Description: (加密接口)
    * @Param: [encode]
    * @return: java.lang.String 
    * @Author: duanwenhao
    * @Date: 2019/9/17 
    */
    @PostMapping(value="/dcparam/ckey")
    String findDcParamByKey(@RequestBody String ckey);

    /**
     * 向web-service发送注册消息
     * @param map
     * @return
     */
    @PostMapping(value = "/client/collparamregistration")
    Map<String,Object> collparamregistion(Map map);

}
