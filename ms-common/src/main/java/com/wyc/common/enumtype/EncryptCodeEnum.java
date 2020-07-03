package com.wyc.common.enumtype;

/**
 * @author: wangyuanchen
 * @date: 2019/11/20 18:50
 */
public enum EncryptCodeEnum {
    // 3des加密
    TRIPLE_DES("1"),
    //CA
    CA("2"),
    //AES
    AES("3"),
    //SM2
    SM2("4"),
    //不加密
    NO_ENCRYTP("0"),
    ;

    private String encryptCode;

    EncryptCodeEnum(String encryptCode) {
        this.encryptCode = encryptCode;
    }

    public String getEncryptCode() {
        return encryptCode;
    }
}