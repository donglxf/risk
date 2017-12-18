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
 * 规则动作定义信息
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@ApiModel
@TableName("rule_action_info")
public class ActionInfo extends Model<ActionInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("action_id")
	@ApiModelProperty(required= true,value = "主键")
	private Long actionId;
    /**
     * 动作类型(1实现2自身)
     */
	@TableField("action_type")
	@ApiModelProperty(required= true,value = "动作类型(1实现2自身)")
	private Integer actionType;
    /**
     * 动作名称
     */
	@TableField("action_name")
	@ApiModelProperty(required= true,value = "动作名称")
	private String actionName;
    /**
     * 动作描述
     */
	@TableField("action_desc")
	@ApiModelProperty(required= true,value = "动作描述")
	private String actionDesc;
    /**
     * 动作实现类(包路径)
     */
	@TableField("action_class")
	@ApiModelProperty(required= true,value = "动作实现类(包路径)")
	private String actionClass;
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


	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
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
		return this.actionId;
	}

	@Override
	public String toString() {
		return "ActionInfo{" +
			"actionId=" + actionId +
			", actionType=" + actionType +
			", actionName=" + actionName +
			", actionDesc=" + actionDesc +
			", actionClass=" + actionClass +
			", isEffect=" + isEffect +
			", creUserId=" + creUserId +
			", creTime=" + creTime +
			", remark=" + remark +
			"}";
	}
}
