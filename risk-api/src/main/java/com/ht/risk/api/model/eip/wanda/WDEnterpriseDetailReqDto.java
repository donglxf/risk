package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WDEnterpriseDetailReqDto {

    @ApiModelProperty(value = "工商注册号或企 业全称或组织机 构代码")
    private String regicode;

    @ApiModelProperty(value = "查询类型")
    private String keyType;

}
