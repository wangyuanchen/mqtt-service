package com.wyc.mqtt.service.aop;

import com.wyc.common.utils.ConstantClassField;
import com.wyc.mqtt.service.annotation.NeedClientKey;
import com.wyc.mqtt.service.enums.MqttErrorCode;
import com.wyc.mqtt.service.exception.ClientKeyIsNullException;
import com.wyc.mqtt.service.feign.ClientServiceInterface;
import com.wyc.mqtt.service.security.ClientKeyContext;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

/**
 * 获取客户端加密密钥
 *
 * @author wangyuanchen
 * @date 2019/11/27 14:28
 */

@Component
@Aspect
@Order(1)
public class ClientKeyResolver {
    private final static Logger log = LoggerFactory.getLogger(ClientKeyResolver.class);

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    @Pointcut("@annotation(com.wyc.mqtt.service.annotation.NeedClientKey)")
    public void myPointCut() {
    }

    @Before(value = "@annotation(needClientKey)")
    public void doBefore(NeedClientKey needClientKey) throws ResourceAccessException, ClientKeyIsNullException {
        log.info("开始取得客户端加密key");
        String clientSecretKey = clientServiceInterface.findDcParamByKey(ConstantClassField.CLIENT_KEY);
        if (StringUtils.isBlank(clientSecretKey)) {
            throw new ClientKeyIsNullException(MqttErrorCode.CLIENT_KEY_ISNULL.getCode(), MqttErrorCode.CLIENT_KEY_ISNULL.getMessage());
        }
//        String clientSecretKey =  "QWERTYUIOPASDFGHJKLZXCVB";
        //将key放入ThreadLocal
        log.info("已经取得客户端加密key，放入上下文");
        ClientKeyContext.init();
        ClientKeyContext.putClientKey(clientSecretKey);
    }
}