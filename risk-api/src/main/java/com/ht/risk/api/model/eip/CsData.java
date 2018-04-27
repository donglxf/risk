package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CsData {
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
