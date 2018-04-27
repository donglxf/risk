package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UrgentRecallDetail implements Serializable {
    private static final long serialVersionUID = -7081110424767294149L;

    /**
     *总通号码数
     */
    @ApiModelProperty(value = "总通号码数")
    private String callTelTotalNums;
    /**
     *总通话次数
     */
    @ApiModelProperty(value = "总通话次数")
    private String callTotalTimes;
    /**
     *主叫次数
     */
    @ApiModelProperty(value = "主叫次数")
    private String callOutTimes;
    /**
     *被叫次数
     */
    @ApiModelProperty(value = "被叫次数")
    private String callInTimes;
    /**
     *通话总时长
     */
    @ApiModelProperty(value = "通话总时长")
    private String callTotalDuration;
    /**
     *通话平均时长
     */
    @ApiModelProperty(value = "通话平均时长")
    private String callAvgDuration;
    /**
     *主叫总时长
     */
    @ApiModelProperty(value = "主叫总时长")
    private String callOutDuration;
    /**
     *被叫总时长
     */
    @ApiModelProperty(value = "被叫总时长")
    private String callInDuration;
    /**
     *通话时长低于15秒的次数
     */
    @ApiModelProperty(value = "通话时长低于15秒的次数")
    private String callDurationBelow15;
    /**
     *通话时长15-30秒的次数
     */
    @ApiModelProperty(value = "通话时长15-30秒的次数")
    private String callDurationBetween15And30;
    /**
     *通话时长60秒以上的次数
     */
    @ApiModelProperty(value = "通话时长60秒以上的次数")
    private String callDurationAbove60;
    /**
     *首次通话时间（只存在overview字段中）
     */
    @ApiModelProperty(value = "首次通话时间")
    private String firstCallTime;
    /**
     *最近一次通话时间（只存在overview字段中）
     */
    @ApiModelProperty(value = "最近一次通话时间")
    private String lastCallTime;
}
