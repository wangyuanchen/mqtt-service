package com.wyc.common.bean;


import com.wyc.common.enumtype.ResultCodeEnum;

import java.util.LinkedHashMap;

/**
 * 返回实体
 *  @author LiuMi
 *  @date 2019年9月11日
 */
public class Result extends LinkedHashMap<String,Object> {

    public Result(String code, String message, Object data) {
        put("code", code);
        put("message", message);
        put("data", data);
    }

    public Result(String code, String message) {
        put("code", code);
        put("message", message);
    }

    public Result(ResultCodeEnum resultEnums) {
        put("code", resultEnums.getCode());
        put("message", resultEnums.getMessage());
    }

    public Result(ResultCodeEnum resultEnums, Object data) {
        put("code", resultEnums.getCode());
        put("message", resultEnums.getMessage());
        put("data", data);
    }
}
