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

