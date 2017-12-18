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
 * 规则基础属性信息表
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@ApiModel
@TableName("rule_property_info")
public class PropertyInfo extends Model<PropertyInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("rule_property_id")
	@ApiModelProperty(required= true,value = "主键")
	private Long rulePropertyId;
    /**
     * 标识
     */
	@TableField("rule_property_identify")
	@ApiModelProperty(required= true,value = "标识")
	private String rulePropertyIdentify;
    /**
     * 名称
     */
	@TableField("rule_property_name")
	@ApiModelProperty(required= true,value = "名称")
	private String rulePropertyName;
    /**
     * 描述
     */
	@TableField("rule_property_desc")
	@ApiModelProperty(required= true,value = "描述")
	private String rulePropertyDesc;
    /**
     * 默认值
     */
	@TableField("default_value")
	@ApiModelProperty(required= true,value = "默认值")
	private String defaultValue;
    /**
     * 是否有效
     */
	@TableField("is_effect")
	@ApiModelProperty(required= true,value = "是否有效")
	private Integer isEffect;
    /**
     * 备注
     */
	@ApiModelProperty(required= true,value = "备注")
	private String remark;


	public Long getRulePropertyId() {
		return rulePropertyId;
	}

	public void setRulePropertyId(Long rulePropertyId) {
		this.rulePropertyId = rulePropertyId;
	}

	public String getRulePropertyIdentify() {
		return rulePropertyIdentify;
	}

	public void setRulePropertyIdentify(String rulePropertyIdentify) {
		this.rulePropertyIdentify = rulePropertyIdentify;
	}

	public String getRulePropertyName() {
		return rulePropertyName;
	}

	public void setRulePropertyName(String rulePropertyName) {
		this.rulePropertyName = rulePropertyName;
	}

	public String getRulePropertyDesc() {
		return rulePropertyDesc;
	}

	public void setRulePropertyDesc(String rulePropertyDesc) {
		this.rulePropertyDesc = rulePropertyDesc;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.rulePropertyId;
	}

	@Override
	public String toString() {
		return "PropertyInfo{" +
			"rulePropertyId=" + rulePropertyId +
			", rulePropertyIdentify=" + rulePropertyIdentify +
			", rulePropertyName=" + rulePropertyName +
			", rulePropertyDesc=" + rulePropertyDesc +
			", defaultValue=" + defaultValue +
			", isEffect=" + isEffect +
			", remark=" + remark +
			"}";
	}
}
