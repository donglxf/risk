package com.ht.risk.rule.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 风控模型离线任务表
 * </p>
 *
 * @author liuzq
 * @since 2018-01-30
 */
@ApiModel
@TableName("risk_model_task")
public class ModelTask extends Model<ModelTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@ApiModelProperty(required= true,value = "主键")
	private Long id;
    /**
     * 模型id
     */
	@TableField("model_procdef_id")
	@ApiModelProperty(required= true,value = "模型id")
	private String modelProcdefId;
    /**
     * corn表达式
     */
	@TableField("corn_text")
	@ApiModelProperty(required= true,value = "corn表达式")
	private String cornText;
    /**
     * 最后更新时间
     */
	@TableField("update_time")
	@ApiModelProperty(required= true,value = "最后更新时间")
	private Date updateTime;
    /**
     * 最后更新人
     */
	@TableField("update_user")
	@ApiModelProperty(required= true,value = "最后更新人")
	private String updateUser;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date createTime;
    /**
     * 创建人
     */
	@TableField("create_user")
	@ApiModelProperty(required= true,value = "创建人")
	private String createUser;
    /**
     * 任务状态1停止2启动
     */
	@TableField("task_status")
	@ApiModelProperty(required= true,value = "任务状态1停止2启动")
	private String taskStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelProcdefId() {
		return modelProcdefId;
	}

	public void setModelProcdefId(String modelProcdefId) {
		this.modelProcdefId = modelProcdefId;
	}

	public String getCornText() {
		return cornText;
	}

	public void setCornText(String cornText) {
		this.cornText = cornText;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ModelTask{" +
			"id=" + id +
			", modelProcdefId=" + modelProcdefId +
			", cornText=" + cornText +
			", taskStatus=" + taskStatus +
			", updateTime=" + updateTime +
			", updateUser=" + updateUser +
			", createTime=" + createTime +
			", createUser=" + createUser +
			"}";
	}
}
