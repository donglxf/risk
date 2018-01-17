package org.ht.risk.log.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.sql.Blob;
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
 * @author 张鹏
 * @since 2018-01-10
 */
@ApiModel
@TableName("RISK_MODELVALIDATE_LOG")
public class ModelValidateLog extends Model<ModelValidateLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,流程运行ID
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键,流程运行ID")
	private String id;
    /**
     * 任务表，批次号ID
     */
	@TableField("BATCH_ID")
	@ApiModelProperty(required= true,value = "任务表，批次号ID")
	private String batchId;
    /**
     * 流程部署ID，与 ACT_RE_PROCDEF.DEPLOYMENT_ID 关联
     */
	@TableField("DEPLOYMEN_TID")
	@ApiModelProperty(required= true,value = "流程部署ID，与 ACT_RE_PROCDEF.DEPLOYMENT_ID 关联")
	private String deploymenTid;
    /**
     * 入参
     */
	@TableField("IN_PARAMTER")
	@ApiModelProperty(required= true,value = "入参")
	private String inParamter;
    /**
     * 出参
     */
	@TableField("OUT_PARAMTER")
	@ApiModelProperty(required= true,value = "出参")
	private String outParamter;
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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getDeploymenTid() {
		return deploymenTid;
	}

	public void setDeploymenTid(String deploymenTid) {
		this.deploymenTid = deploymenTid;
	}

	public String getInParamter() {
		return inParamter;
	}

	public void setInParamter(String inParamter) {
		this.inParamter = inParamter;
	}

	public String getOutParamter() {
		return outParamter;
	}

	public void setOutParamter(String outParamter) {
		this.outParamter = outParamter;
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
		return "ModelvalidateLog{" +
			"id=" + id +
			", batchId=" + batchId +
			", deploymenTid=" + deploymenTid +
			", inParamter=" + inParamter +
			", outParamter=" + outParamter +
			", createTime=" + createTime +
			", createUser=" + createUser +
			"}";
	}
}
