package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class IdVerifyReqDto extends commEntryIn {
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
