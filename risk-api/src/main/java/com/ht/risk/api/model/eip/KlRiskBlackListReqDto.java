package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 考拉风险黑名单
 * @author:喻尊龙
 * @date: 2018/3/7
 */
@Data
public class KlRiskBlackListReqDto  extends commEntryIn implements Serializable {
    private static final long serialVersionUID = 8460697605945497358L;

    /**
     *真实姓名
     */
    @NotBlank
    private String realName;
    /**
     *证件证号
     */
    @NotBlank
    private String identityCard;
    /**
     *手机号码
     */
    @NotBlank
    private String mobilePhone;

}
