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
public class TestDroolsLog  {


	private Long procinstId;

	private String modelName;

	private String senceVersionid;

	private String inParamter;

	private String outParamter;

	private Integer executeTotal;

	private String type;

	private Date createTime;

	private Long batchId;

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
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

}
