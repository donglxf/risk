package com.ht.risk.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author dyb
 * @since 2018-01-18
 */
@ApiModel
@TableName("risk_test_drools_log")
public class TestDroolsLog extends Model<TestDroolsLog> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@ApiModelProperty(required= true,value = "")
	private Long id;
    /**
     * 模型实例id
     */
	@TableField("procinst_id")
	@ApiModelProperty(required= true,value = "模型实例id")
	private Long procinstId;
    /**
     * 模型名
     */
	@TableField("model_name")
	@ApiModelProperty(required= true,value = "模型名")
	private String modelName;
    /**
     * 決策版本流水
     */
	@TableField("sence_versionid")
	@ApiModelProperty(required= true,value = "決策版本流水")
	private String senceVersionid;
    /**
     * 入参
     */
	@TableField("in_paramter")
	@ApiModelProperty(required= true,value = "入参")
	private String inParamter;
    /**
     * 计算结果
     */
	@TableField("out_paramter")
	@ApiModelProperty(required= true,value = "计算结果")
	private String outParamter;
    /**
     * 命中规则总数
     */
	@TableField("execute_total")
	@ApiModelProperty(required= true,value = "命中规则总数")
	private Integer executeTotal;
    /**
     * 决策执行类型：0-直接调用，1-模型调用
     */
	@TableField("type")
	@ApiModelProperty(required= true,value = "决策执行类型：0-直接调用，1-模型调用")
	private String type;
    /**
     * 插入时间
     */
	@TableField("create_time")
	@ApiModelProperty(required= true,value = "插入时间")
	private Date createTime;

	/**
     * 验证批次号
     */
	@TableField("batch_id")
	@ApiModelProperty(required= true,value = "验证批次号")
	private Long batchId;

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProcinstId() {
		return procinstId;
	}

	public void setProcinstId(Long procinstId) {
		this.procinstId = procinstId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getSenceVersionid() {
		return senceVersionid;
	}

	public void setSenceVersionid(String senceVersionid) {
		this.senceVersionid = senceVersionid;
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

	public Integer getExecuteTotal() {
		return executeTotal;
	}

	public void setExecuteTotal(Integer executeTotal) {
		this.executeTotal = executeTotal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TestDroolsLog{" +
			"id=" + id +
			", procinstId=" + procinstId +
			", modelName=" + modelName +
			", senceVersionid=" + senceVersionid +
			", inParamter=" + inParamter +
			", outParamter=" + outParamter +
			", executeTotal=" + executeTotal +
			", type=" + type +
			", createTime=" + createTime +
			"}";
	}
}
