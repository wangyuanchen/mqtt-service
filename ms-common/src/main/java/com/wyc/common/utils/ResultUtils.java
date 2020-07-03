package com.wyc.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装返回外层报文
 *
 * @author qinyunwei
 * @date 2019-09-17
 */
public class ResultUtils {

    /**
     * @Description: (解密请求报文)
     * @Param: [param]
     * @return: java.lang.String
     * @Author: qinyunwei
     * @Date: 2019-09-17
     */
    public static String decodemessage(String param, String key) {
        JSONObject obj = JSONObject.parseObject(param);
        String content = obj.getString("content");
        //String decode = Base64Utils.decode(content);
        return TriPleDesUtil.decode3Des(content, key);
    }

    /**
     * @Description: (封装返回报文)
     * @Param: [content 需要加密内层报文]
     * @return: java.lang.String
     * @Author: qinyunwei
     * @Date: 2019-09-17
     */
    public static String encodemessage(String content, String key) {
        Map Resultmap = new HashMap();
        try {
            String GBKStr = URLEncoder.encode(content, "GBK");
            Resultmap.put("appid", "");
            Resultmap.put("zipCode", ConstantClassField.zipCode0);
            Resultmap.put("encryptCode", ConstantClassField.encryptCode2);
            Resultmap.put("content", TriPleDesUtil.encode3Des(content, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(Resultmap);
    }

    public static String encodemessage2(String code, String message) {
        Map Resultmap = new HashMap();
        try {
            Resultmap.put("appid", "");
            // TODO 需要定义常量
            Resultmap.put("zipCode", ConstantClassField.zipCode0Str);
            Resultmap.put("encryptCode", ConstantClassField.encryptCode1Str);
            Resultmap.put("code", code);
            Resultmap.put("message", message);
            Resultmap.put("content", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(Resultmap);
    }

    public static String encodemessage3(String code, String message, Map map) {
        Map Resultmap = new HashMap();
        try {
            Resultmap.put("appid", "");
            Resultmap.put("zipCode", ConstantClassField.zipCode0);
            Resultmap.put("encryptCode", ConstantClassField.encryptCode2);
            Resultmap.put("code", code);
            Resultmap.put("message", message);
            Resultmap.put("content", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(Resultmap);
    }

    public static String encodemessage4(String code, String message, Object object, String key) {
        Map Resultmap = new HashMap();
        try {
            Resultmap.put("appid", "");
            Resultmap.put("zipCode", ConstantClassField.zipCode0Str);
            Resultmap.put("encryptCode", ConstantClassField.encryptCode1Str);
            Resultmap.put("code", code);
            Resultmap.put("message", message);
            String content = TriPleDesUtil.encode3Des(JSON.toJSONString(object), key);
            Resultmap.put("content", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(Resultmap);
    }

    /**
     * @Description: (封装返回报文)
     * @Param: [content 需要加密内层报文]
     * @return: Map
     * @Author: lidanyang
     * @Date: 2019-09-17
     */
    public static Map<String, Object> encodemessageUsercenter(String content, String key) {
        Map resultmap = new HashMap();
        try {
            String GBKStr = URLEncoder.encode(content, "GBK");
            resultmap.put("appid", "");
            resultmap.put("zipCode", ConstantClassField.zipCode0);
            resultmap.put("encryptCode", ConstantClassField.encryptCode2);
            resultmap.put("content", TriPleDesUtil.encode3Des(content, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultmap;
    }
}
