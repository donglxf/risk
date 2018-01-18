package com.ht.risk.rule.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>
 * 
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-17
 */
@ApiModel
@TableName("RISK_RULE_HIS_VERSION")
public class RuleHisVersion extends Model<RuleHisVersion> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键")
	private Long id;
    /**
     * 決策版本流水
     */
	@TableField("SENCE_VERSIONID")
	@ApiModelProperty(required= true,value = "決策版本流水")
	private Long senceVersionId;
    /**
     * 规则名称
     */
	@TableField("RULE_NAME")
	@ApiModelProperty(required= true,value = "规则名称")
	private String ruleName;
    /**
     * 规则描述
     */
	@TableField("RULE_DESC")
	@ApiModelProperty(required= true,value = "规则描述")
	private String ruleDesc;
    /**
     * 是否生效：0-有效，1-无效
     */
	@TableField("IS_EFFECT")
	@ApiModelProperty(required= true,value = "是否生效：0-有效，1-无效")
	private String isEffect;
    /**
     * 创建用户
     */
	@TableField("CREATE_USER")
	@ApiModelProperty(required= true,value = "创建用户")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSenceVersionId() {
		return senceVersionId;
	}

	public void setSenceVersionId(Long senceVersionId) {
		this.senceVersionId = senceVersionId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RuleHisVersion{" +
			"id=" + id +
			", senceVersionid=" + senceVersionId +
			", ruleName=" + ruleName +
			", ruleDesc=" + ruleDesc +
			", isEffect=" + isEffect +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}
