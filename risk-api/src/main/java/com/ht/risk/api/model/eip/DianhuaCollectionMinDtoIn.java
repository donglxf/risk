package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class DianhuaCollectionMinDtoIn extends commEntryIn {

    @ApiModelProperty(value = "查询的手机号码")
    private String mobilePhone;

    @ApiModelProperty(value = "通话详单")
    private List<CallLog> callLog;

    @ApiModelProperty(value = "客户端对用户的唯一标识")
    private String uid;

}

