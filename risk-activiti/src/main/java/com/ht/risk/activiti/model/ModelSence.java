package com.ht.risk.activiti.model;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.KeySequence;
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
 * @since 2018-01-05
 */
@ApiModel
@TableName("ACT_MODEL_SENCE")
public class ModelSence extends Model<ModelSence> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键")
	private Long id;
    /**
     * 流程部署ID
     */
	@TableField("DEPLOYMENTID")
	@ApiModelProperty(required= true,value = "流程部署ID")
	private String deploymentid;
    /**
     * 策略编码
     */
	@TableField("SENCECODE")
	@ApiModelProperty(required= true,value = "策略编码")
	private String sencecode;
    /**
     * 版本
     */
	@TableField("VERSION")
	@ApiModelProperty(required= true,value = "版本")
	private String version;
    /**
     * 创建时间
     */
	@TableField("GENERATED_")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date generated;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeploymentid() {
		return deploymentid;
	}

	public void setDeploymentid(String deploymentid) {
		this.deploymentid = deploymentid;
	}

	public String getSencecode() {
		return sencecode;
	}

	public void setSencecode(String sencecode) {
		this.sencecode = sencecode;
	}


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getGenerated() {
		return generated;
	}

	public void setGenerated(Date generated) {
		this.generated = generated;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ModelSence{" +
			"id=" + id +
			", deploymentid=" + deploymentid +
			", sencecode=" + sencecode +
			", version=" + version +
			", generated=" + generated +
			"}";
	}
}
