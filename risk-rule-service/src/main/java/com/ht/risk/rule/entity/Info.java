package com.ht.risk.rule.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 规则信息
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@ApiModel
@TableName("rule_info")
public class Info extends Model<Info> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("rule_id")
	@ApiModelProperty(required= true,value = "主键")
	private Long ruleId;
    /**
     * 场景
     */
	@TableField("scene_id")
	@ApiModelProperty(required= true,value = "场景")
	private Long sceneId;
    /**
     * 名称
     */
	@TableField("rule_name")
	@ApiModelProperty(required= true,value = "名称")
	private String ruleName;
    /**
     * 描述
     */
	@TableField("rule_desc")
	@ApiModelProperty(required= true,value = "描述")
	private String ruleDesc;
    /**
     * 是否启用
     */
	@TableField("rule_enabled")
	@ApiModelProperty(required= true,value = "是否启用")
	private Integer ruleEnabled;
    /**
     * 是否有效
     */
	@TableField("is_effect")
	@ApiModelProperty(required= true,value = "是否有效")
	private Integer isEffect;
    /**
     * 创建人
     */
	@TableField("cre_user_id")
	@ApiModelProperty(required= true,value = "创建人")
	private Long creUserId;
    /**
     * 创建时间
     */
	@TableField("cre_time")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date creTime;
    /**
     * 备注
     */
	@ApiModelProperty(required= true,value = "备注")
	private String remark;


	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
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

	public Integer getRuleEnabled() {
		return ruleEnabled;
	}

	public void setRuleEnabled(Integer ruleEnabled) {
		this.ruleEnabled = ruleEnabled;
	}

	public Integer getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect;
	}

	public Long getCreUserId() {
		return creUserId;
	}

	public void setCreUserId(Long creUserId) {
		this.creUserId = creUserId;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.ruleId;
	}

	@Override
	public String toString() {
		return "Info{" +
			"ruleId=" + ruleId +
			", sceneId=" + sceneId +
			", ruleName=" + ruleName +
			", ruleDesc=" + ruleDesc +
			", ruleEnabled=" + ruleEnabled +
			", isEffect=" + isEffect +
			", creUserId=" + creUserId +
			", creTime=" + creTime +
			", remark=" + remark +
			"}";
	}
}
