package com.wyc.mqtt.service.feign.fallback;

import com.wyc.mqtt.service.feign.ClientServiceInterface;
import feign.hystrix.FallbackFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangyuanchen
 * @date: 2019-11-27 15:01
 * @description:
 */
public class ClientServiceInterfaceFallbackFactory implements FallbackFactory<ClientServiceInterface> {
    @Override
    public ClientServiceInterface create(Throwable throwable) {
        return new ClientServiceInterface() {
            @Override
            public String findDcParamByKey(String ckey) {
                return null;
            }

            @Override
            public Map<String, Object> collparamregistion(Map map) {
                Map map1 = new HashMap();
                map1.put("code", "400");
                map1.put("message", "服务断开");
                return map1;
            }
        };
    }
}
