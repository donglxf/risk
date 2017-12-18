package com.ht.risk.rule.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 规则属性配置表
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@ApiModel
@TableName("rule_property_rel")
public class PropertyRel extends Model<PropertyRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("rule_pro_rel_id")
	@ApiModelProperty(required= true,value = "主键")
	private Long ruleProRelId;
    /**
     * 规则
     */
	@TableField("rule_id")
	@ApiModelProperty(required= true,value = "规则")
	private Long ruleId;
    /**
     * 规则属性
     */
	@TableField("rule_property_id")
	@ApiModelProperty(required= true,value = "规则属性")
	private Long rulePropertyId;
    /**
     * 规则属性值
     */
	@TableField("rule_property_value")
	@ApiModelProperty(required= true,value = "规则属性值")
	private String rulePropertyValue;


	public Long getRuleProRelId() {
		return ruleProRelId;
	}

	public void setRuleProRelId(Long ruleProRelId) {
		this.ruleProRelId = ruleProRelId;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long getRulePropertyId() {
		return rulePropertyId;
	}

	public void setRulePropertyId(Long rulePropertyId) {
		this.rulePropertyId = rulePropertyId;
	}

	public String getRulePropertyValue() {
		return rulePropertyValue;
	}

	public void setRulePropertyValue(String rulePropertyValue) {
		this.rulePropertyValue = rulePropertyValue;
	}

	@Override
	protected Serializable pkVal() {
		return this.ruleProRelId;
	}

	@Override
	public String toString() {
		return "PropertyRel{" +
			"ruleProRelId=" + ruleProRelId +
			", ruleId=" + ruleId +
			", rulePropertyId=" + rulePropertyId +
			", rulePropertyValue=" + rulePropertyValue +
			"}";
	}
}
