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
 * 
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
@ApiModel
@TableName("risk_validate_batch")
public class ValidateBatch extends Model<ValidateBatch> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,批次号
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键,批次号")
	private String id;
    /**
     * 流程部署ID，与 ACT_RE_PROCDEF.DEPLOYMENT_ID 关联
     */
	@TableField("DEPLOYMEN_TID")
	@ApiModelProperty(required= true,value = "流程部署ID，与 ACT_RE_PROCDEF.DEPLOYMENT_ID 关联")
	private String deploymenTid;
    /**
     * 批次大小
     */
	@TableField("BATCH_SIZE")
	@ApiModelProperty(required= true,value = "批次大小")
	private Integer batchSize;
    /**
     * 是否生效：0-有效，1-无效
     */
	@TableField("IS_EFFECT")
	@ApiModelProperty(required= true,value = "是否生效：0-有效，1-无效")
	private String isEffect;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date createTime;
    /**
     * 创建用户
     */
	@TableField("CREATE_USER")
	@ApiModelProperty(required= true,value = "创建用户")
	private String createUser;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeploymenTid() {
		return deploymenTid;
	}

	public void setDeploymenTid(String deploymenTid) {
		this.deploymenTid = deploymenTid;
	}

	public Integer getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}

	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ValidateBatch{" +
			"id=" + id +
			", deploymenTid=" + deploymenTid +
			", batchSize=" + batchSize +
			", isEffect=" + isEffect +
			", createTime=" + createTime +
			", createUser=" + createUser +
			"}";
	}
}
