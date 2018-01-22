package com.ht.risk.activiti.entity;

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
 * @since 2018-01-19
 */
@ApiModel
@TableName("ACT_EXCUTE_TASK")
public class ActExcuteTask extends Model<ActExcuteTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键")
	private Long id;

	/**
	 * 主键
	 */
	@TableId("BATCH_ID")
    private Long batchId;

    /**
     * 模型版本ID
     */
	@TableField("PROC_RELEASE_ID")
	@ApiModelProperty(required= true,value = "模型版本ID")
	private Long procReleaseId;
    /**
     * 流程运行实例ID
     */
	@TableField("PROC_INST_ID")
	@ApiModelProperty(required= true,value = "流程运行实例ID")
	private String procInstId;
    /**
     * 任务状态，0-待执行，1-执行中，2-执行结束
     */
	@TableField("STATUS")
	@ApiModelProperty(required= true,value = "任务状态，0-待执行，1-执行中，2-执行结束,3-执行异常，4-启动异常")
	private String status;
    /**
     * 任务类型，0-验证任务，1-业务系统调用
     */
	@TableField("TYPE")
	@ApiModelProperty(required= true,value = "任务类型，0-验证任务，1-业务系统调用")
	private String type;
    /**
     * 备注
     */
	@TableField("REMARK")
	@ApiModelProperty(required= true,value = "备注")
	private String remark;
    /**
     * 更新时间
     */
	@TableField("END_TIME")
	@ApiModelProperty(required= true,value = "更新时间")
	private Date endTime;
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

	public Long getProcReleaseId() {
		return procReleaseId;
	}

	public void setProcReleaseId(Long procReleaseId) {
		this.procReleaseId = procReleaseId;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	@Override
	public String toString() {
		return "ActExcuteTask{" +
			"id=" + id +
			", procReleaseId=" + procReleaseId +
			", procInstId=" + procInstId +
			", status=" + status +
			", type=" + type +
			", remark=" + remark +
			", endTime=" + endTime +
			", createTime=" + createTime +
			", createUser=" + createUser +
			"}";
	}
}
