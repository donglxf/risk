package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ContactFastReqDto extends commEntryIn {
    private static final long serialVersionUID = 638666325566033420L;

    /**
     * 催收分接口返回的唯一标识
     */
    @ApiModelProperty(value = "催收分接口返回的唯一标识")
    @NotBlank
    private String sid;

}
