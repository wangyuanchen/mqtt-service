package com.wyc.mqtt.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户端秘钥处理
 *
 * @author wangyuanchen
 * @date 2019/11/27
 */
public class ClientKeyContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientKeyContext.class);
    public static final ThreadLocal<Map<String, Object>> CONTEXT_DATA = new ThreadLocal<>();
    public static final String CLIENT_KEY = "clientKey";

    public ClientKeyContext() {
    }

    public static String getClientKey() {
        return getStringValue(CLIENT_KEY);
    }

    public static void putClientKey(Object value) {
        put(CLIENT_KEY, value);
    }

    public static void put(String key, Object value) {
        CONTEXT_DATA.get().put(key, value);
    }

    public static void init() {
        CONTEXT_DATA.set(new HashMap<>(8));
    }

    public static void remove() {
        CONTEXT_DATA.remove();
    }

    private static String getIntegerValue(String key) {
        if (CONTEXT_DATA.get() == null) {
            return null;
        } else {
            Object value =  CONTEXT_DATA.get().get(key);
            return value == null ? null : value.toString();
        }
    }

    private static String getStringValue(String key) {
        if (CONTEXT_DATA.get() == null) {
            return null;
        } else {
            Object value = CONTEXT_DATA.get().get(key);
            return value == null ? null : (String) value;
        }
    }

}
