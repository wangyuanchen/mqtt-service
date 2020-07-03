package com.wyc.common.bean;

import lombok.Data;

/**
 * 客户端响应的通用外层报文类
 *  @author zhaoliang
 *  @date 2019年11月06日
 */
@Data
public class ClientRes {
    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MESSAGE = "处理成功";
    private String zipCode;
    private String encryptCode;
    private String code;
    private String message;
    private String content;
}
