package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Allmsglist {
    @ApiModelProperty(value = "信息类型，请参考案件类型")
    private int bigtype;

    @ApiModelProperty(value = "数据来源")
    private String source;

    @ApiModelProperty(value = "采集时间,格式：yyyy-MM-dd")
    private String posttime;

    @ApiModelProperty(value = "案件编号")
    private String casenum;

    @ApiModelProperty(value = "案件类型")
    private String type;

    @ApiModelProperty(value = "案件时间，格式：yyyy-MM-dd")
    private String datetime;

    @ApiModelProperty(value = "案件地点")
    private String address;

    @ApiModelProperty(value = "涉案金额")
    private String money;
}
