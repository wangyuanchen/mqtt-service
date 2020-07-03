package com.wyc.mqtt.service.entity;

import com.wyc.mqtt.service.validation.CheckTaxNo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: wangyuanchen
 * @date: 2019-11-20 16:05
 * @description:
 */
@Data
public class MqttReq {

    /**
     * 地区编码
     */
    @NotNull(message = "地区编码字段不能为空！")
    private String areaCode;
    /**
     * 企业税号
     */
    @NotBlank(message = "企业税号不能为空！")
    @CheckTaxNo
    private String taxpayerId;
    /**
     * 分机号
     */
    @NotBlank(message = "分机号不能为空！")
    private String machineNo;
    /**
     *业务类型 0:注册文件，1:业务数据
     */
    @NotBlank(message = "业务类型不能为空！")
    private String type;
    /**
     * 内容主题
     */
    @NotNull(message = "数据包字段不能为空！")
    private String datagram;
}
