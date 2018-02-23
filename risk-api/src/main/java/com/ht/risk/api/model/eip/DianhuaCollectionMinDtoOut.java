package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DianhuaCollectionMinDtoOut {
    private static final long serialVersionUID = -5467566816263892329L;

    /**
     * 催收分接口返回参数
     */
    @ApiModelProperty(value = "催收分接口返回参数")
    private CsData csData;

}

@Data
class CsData{
    private static final long serialVersionUID = -9081738308768547930L;

    /**
     *催收参数唯一标识
     */
    @ApiModelProperty(value = "催收参数唯一标识")
    private String sid;
    /**
     *查询的号码
     */
    @ApiModelProperty(value = "查询的号码")
    private String tel;
    /**
     *用户传入的唯一标识
     */
    @ApiModelProperty(value = "用户传入的唯一标识")
    private String uid;
    /**
     *时间戳，催收参数计算的基准时间
     */
    @ApiModelProperty(value = "时间戳，催收参数计算的基准时间")
    private String time;
    /**
     *传入总记录数
     */
    @ApiModelProperty(value = "传入总记录数")
    private String totalNum;
    /**
     *有效数据数量
     */
    @ApiModelProperty(value = "有效数据数量")
    private String effectiveNum;
    /**
     *全部记录的总览，参考content详情
     */
    @ApiModelProperty(value = "全部记录的总览")
    private UrgentRecallContent overview;
    /**
     *近一周内通话记录中催收信息，参考content详情
     */
    @ApiModelProperty(value = "近一周内通话记录中催收信息")
    private UrgentRecallContent lastWeek;
    /**
     *近两周内催收信息
     */
    @ApiModelProperty(value = "近两周内催收信息")
    private UrgentRecallContent lastTwoWeeks;
    /**
     *近三周内催收信息
     */
    @ApiModelProperty(value = "近三周内催收信息")
    private UrgentRecallContent lastThreeWeeks;
    /**
     *近30天内催收信息，参考content详情
     */
    @ApiModelProperty(value = "近30天内催收信息")
    private UrgentRecallContent last30Days;
    /**
     *近30-60天催收信息
     */
    @ApiModelProperty(value = "近30-60天催收信息")
    private UrgentRecallContent last30And60Days;
    /**
     *近60-90天催收信息，参考content详情
     */
    @ApiModelProperty(value = "近60-90天催收信息")
    private UrgentRecallContent last60And90Days;

}

@Data
class UrgentRecallDetail implements Serializable {

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
@Data
class UrgentRecallContent implements Serializable {

    private static final long serialVersionUID = 6260356377073142231L;

    private UrgentRecallDetail dunning;
    private UrgentRecallDetail notSureDunning;

}
