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
 * @since 2018-01-17
 */
@ApiModel
@TableName("RISK_VALIDATE_BATCH")
public class ValidateBatch extends Model<ValidateBatch> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,批次号
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键,批次号")
	private Long id;
    /**
     * 流程部署ID，与 ACT_RE_PROCDEF.DEPLOYMENT_ID 关联
     */
	@TableField("DEPLOYMENT_ID")
	@ApiModelProperty(required= true,value = "流程部署ID，与 ACT_RE_PROCDEF.DEPLOYMENT_ID 关联")
	private String deploymentId;
    /**
     * 批次大小
     */
	@TableField("BATCH_SIZE")
	@ApiModelProperty(required= true,value = "批次大小")
	private Integer batchSize;
    /**
     * 批次状态，0-待执行，1-正在执行，2-执行完成，3-执行异常
     */
	@TableField("STATUS")
	@ApiModelProperty(required= true,value = "批次状态，0-待执行，1-正在执行，2-执行完成，3-执行异常")
	private String status;
    /**
     * 执行次数
     */
	@TableField("COUNT")
	@ApiModelProperty(required= true,value = "执行次数")
	private Integer count;
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public Integer getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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
			", deploymentId=" + deploymentId +
			", batchSize=" + batchSize +
			", status=" + status +
			", count=" + count +
			", isEffect=" + isEffect +
			", createTime=" + createTime +
			", createUser=" + createUser +
			"}";
	}
}
