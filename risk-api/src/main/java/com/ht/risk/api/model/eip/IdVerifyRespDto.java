package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class IdVerifyRespDto {
    private static final long serialVersionUID = -4358639175479671606L;
    /**
     * 本次请求的id
     */
    @ApiModelProperty(value = "本次请求的id")
    private String requestId;
    /**
     * 验证结果,1:身份证号和姓名一致;2:身份证号和姓名不一致;3:查无此身份证号
     */
    @ApiModelProperty(value = "验证结果,1:身份证号和姓名一致;2:身份证号和姓名不一致;3:查无此身份证号")
    private String result;

}
