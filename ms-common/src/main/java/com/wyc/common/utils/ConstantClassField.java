package com.wyc.common.utils;

/**
 * @author duanwenhao
 * @version V1.0
 * @Package com.aisino.social.common.utils
 * @Description: (常量类)
 * @date 2019/9/16 -   19:53
 */
public class ConstantClassField {

    //客户端加密key值
    public static final String CLIENT_KEY = "client_key";
    //返回值appid
    public static final String APPID = "appid";

    //压缩标识
    public static final int zipCode0 = 0;
    public static final int zipCode1 = 1;

    public static final String zipCode0Str = "0";
    public static final String zipCode1Str = "1";

    //加密标识  0表示只做BASE64，1表示AES加密，2表示3DES加密，3表示SM4加密
    public static final int encryptCode0 = 0;
    public static final int encryptCode1 = 1;
    public static final int encryptCode2 = 2;
    public static final int encryptCode3 = 3;

    public static final String encryptCode0Str = "0";
    public static final String encryptCode1Str = "1";
    public static final String encryptCode2Str = "2";
    public static final String encryptCode3Str = "3";


    //财税资讯客户端首页key
    public static final String REDIS_FINANCIAL_NEWS_ONE="redis_financial_news_one";

    //更新完状态给发票服务传递消息
    public static final String KAFKA_INVOICE_TOPIC_OUT = "InvoiceStatusMessage";
}
