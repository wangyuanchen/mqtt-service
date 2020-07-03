package com.wyc.mqtt.service.exception;

import com.wyc.common.exception.SystemException;
import lombok.Data;

/**
 * 入参校验错误异常类
 *
 * @author wangyuanchen
 * @date 2019/11/27
 */
@Data
public class ClientKeyIsNullException extends SystemException {

    public ClientKeyIsNullException(String code, String message) {
        super(code, message);
    }

}
