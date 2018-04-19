package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ConsensusReqDto extends commEntryIn {
    private static final long serialVersionUID = 7732828095608960562L;
    /**
     *身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    private String idcard;
    /**
     *姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     *手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile_phone;

}
