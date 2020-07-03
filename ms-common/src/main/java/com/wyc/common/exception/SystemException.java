package com.wyc.common.exception;

import lombok.Data;

/**
 * 系统异常类
 *
 * @author zhaoliang
 * @date 2019/11/11
 */
@Data
public class SystemException extends RuntimeException {
    private String code;
    private String message;

    public SystemException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public SystemException(String code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }
}
