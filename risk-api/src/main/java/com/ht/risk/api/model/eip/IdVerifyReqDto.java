package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class IdVerifyReqDto {
    private static final long serialVersionUID = 4519808852267261707L;

    /**
     *证件证号
     */
    @ApiModelProperty(value = "证件证号")
    private String identityCard;
    /**
     *真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;

}
