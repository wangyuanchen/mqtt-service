package com.wyc.common.enumtype;

/**
 * @author: guoyi
 * @date: 2019/11/20 18:50
 */
public enum ZipCodeEnum {
    //不压缩
    NO_ZIP("0"),
    YES_ZIP("1"),
    ;

    private final String zipCode;

    ZipCodeEnum(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }
}