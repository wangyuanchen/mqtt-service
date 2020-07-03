package com.wyc.common.bean;

import com.wyc.common.validation.CheckEncryptCode;
import com.wyc.common.validation.CheckZipCode;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 客户端请求的通用外层报文类
 *  @author zhaoliang
 *  @date 2019年11月06日
 */
@Data
public class ClientReq {
    @NotBlank(message = "appid不能为空")
    private String appid;
    @CheckZipCode(message = "zipCode值必须为0或者1")
    private String zipCode;
    @CheckEncryptCode(message = "encryptCode参数错误")
    private String encryptCode;
    @NotBlank(message = "content不能为空")
    private String content;
}
