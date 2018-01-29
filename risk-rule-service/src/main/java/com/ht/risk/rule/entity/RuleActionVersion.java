package com.ht.risk.rule.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 
 * </p>
 *
 * @author zhangpeng
 * @since 2018-01-29
 */
@ApiModel
@TableName("risk_rule_action_version")
public class RuleActionVersion extends Model<RuleActionVersion> {

    private static final long serialVersionUID = 1L;

    /**
     * 动作版本关联id
     */
	@TableId(value="risk_rule_action_version_id", type= IdType.AUTO)
	@ApiModelProperty(required= true,value = "动作版本关联id")
	private Long riskRuleActionVersionId;
    /**
     * 版本id
     */
	@TableField("version_id")
	@ApiModelProperty(required= true,value = "版本id")
	private Long versionId;
    /**
     * 实体类的名
     */
	@TableField("action_class")
	@ApiModelProperty(required= true,value = "实体类的名")
	private String actionClass;
    /**
     * 动作id
     */
	@TableField("action_id")
	@ApiModelProperty(required= true,value = "动作id")
	private Long actionId;


	public Long getRiskRuleActionVersionId() {
		return riskRuleActionVersionId;
	}

	public void setRiskRuleActionVersionId(Long riskRuleActionVersionId) {
		this.riskRuleActionVersionId = riskRuleActionVersionId;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	@Override
	protected Serializable pkVal() {
		return this.riskRuleActionVersionId;
	}

	@Override
	public String toString() {
		return "RuleActionVersion{" +
			"riskRuleActionVersionId=" + riskRuleActionVersionId +
			", versionId=" + versionId +
			", actionClass=" + actionClass +
			", actionId=" + actionId +
			"}";
	}
}
