package com.wyc.mqtt.service.exception.handler;

import com.wyc.mqtt.service.entity.MqttRes;
import com.wyc.mqtt.service.enums.MqttErrorCode;
import com.wyc.mqtt.service.exception.ClientKeyIsNullException;
import com.wyc.mqtt.service.exception.JsonValidationException;
import com.wyc.mqtt.service.utils.ReturnMessageUtil;
import com.alibaba.fastjson.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

/**
 * mqtt服务异常处理
 *
 * @author wangyuanchen
 * @date 2019/11/27
 */
@RestControllerAdvice(basePackages = "com.aisino.social.mqtt.service.controller")
public class MqttServiceExceptionHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(MqttServiceExceptionHandler.class);

    /**
     * 全局处理NullPoint异常捕捉处理
     *
     * @param ex NullPointerException
     * @return 统一返回格式
     */
    @ExceptionHandler(value = NullPointerException.class)
    public MqttRes handlerNullPointerException(NullPointerException ex) {
        LOGGER.error("catch NullPointerException error", ex);
        return ReturnMessageUtil.setReturnMessage(MqttErrorCode.SYSTEM_ERROR.getCode(),MqttErrorCode.SYSTEM_ERROR.getMessage());
    }

    /**
     * 全局处理ClientKeyIsNullException异常捕捉处理
     *
     * @param ex ClientKeyIsNullException
     * @return 统一返回格式
     */
    @ExceptionHandler(value = ClientKeyIsNullException.class)
    public MqttRes handlerClientKeyIsNullException(ClientKeyIsNullException ex) {
        LOGGER.error("catch ClientKeyIsNullException error", ex);
        return ReturnMessageUtil.setReturnMessage(MqttErrorCode.CLIENT_KEY_ISNULL.getCode(),MqttErrorCode.CLIENT_KEY_ISNULL.getMessage());
    }

    /**
     * 全局处理JSONException异常捕捉处理
     *
     * @param ex JSONException异常
     * @return 统一返回格式
     */
    @ExceptionHandler(value = JSONException.class)
    public MqttRes handlerJsonException(JSONException ex) {
        LOGGER.info("catch JSONException error", ex);
        return ReturnMessageUtil.setReturnMessage(MqttErrorCode.PARAM_TYPE_FAILURE.getCode(),MqttErrorCode.PARAM_TYPE_FAILURE.getMessage());
    }

    /**
     * 全局处理JsonValidationException异常捕捉处理
     *
     * @param ex JsonValidationException异常
     * @return 统一返回格式
     */
    @ExceptionHandler(value = JsonValidationException.class)
    public MqttRes handlerJsonValidationException(JsonValidationException ex) {
        LOGGER.error("catch JsonValidationException error", ex);
        StringBuilder messageBuilder = new StringBuilder(MqttErrorCode.PARAM_FAILURE.getMessage());
        ex.getViolations().forEach(violation -> messageBuilder.append(";").append(violation.getPropertyPath()).append(";").append(violation.getMessage()));
        LOGGER.error(messageBuilder.toString());
        return ReturnMessageUtil.setReturnMessage(MqttErrorCode.PARAM_FAILURE.getCode(),messageBuilder.toString());
    }

    /**
     * 全局处理MethodArgumentNotValidException异常捕捉处理
     *
     * @param ex MethodArgumentNotValidException异常
     * @return 统一返回格式
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public MqttRes handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        LOGGER.error("catch MethodArgumentNotValidException error", ex);
        StringBuilder messageBuilder = new StringBuilder(MqttErrorCode.PARAM_FAILURE.getMessage());
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> messageBuilder.append(";").append(fieldError.getDefaultMessage()));
        LOGGER.error(messageBuilder.toString());
        return ReturnMessageUtil.setReturnMessage(MqttErrorCode.PARAM_FAILURE.getCode(),messageBuilder.toString());
    }

    /**
     * 全局处理IllegalArgumentException异常捕捉处理
     *
     * @param ex IllegalArgumentException异常
     * @return 统一返回格式
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public MqttRes handlerIllegalArgumentException(IllegalArgumentException ex) {
        LOGGER.error("catch IllegalArgumentException error", ex);
        return ReturnMessageUtil.setReturnMessage(MqttErrorCode.OBJECT_ISNULL.getCode(),MqttErrorCode.OBJECT_ISNULL.getMessage());
    }

    /**
     * 全局处理ResourceAccessException异常捕捉处理
     *
     * @param ex ResourceAccessException异常
     * @return 统一返回格式
     */
    @ExceptionHandler(value = ResourceAccessException.class)
    public MqttRes handlerResourceAccessException(ResourceAccessException ex) {
        LOGGER.error("catch ResourceAccessException error", ex);
        return ReturnMessageUtil.setReturnMessage(MqttErrorCode.TIMEOUT_FAILURE.getCode(),MqttErrorCode.TIMEOUT_FAILURE.getMessage());
    }


}
