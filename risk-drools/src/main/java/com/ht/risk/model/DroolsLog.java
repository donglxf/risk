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
 * @author 张鹏
 * @since 2018-01-10
 */
@ApiModel
@TableName("RISK_DROOLS_LOG")
public class DroolsLog extends Model<DroolsLog> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
	@ApiModelProperty(required= true,value = "")
	private Long id;
    /**
     * 模型实例id
     */
	@TableField("PROCINST_ID")
	@ApiModelProperty(required= true,value = "模型实例id")
	private Long procinstId;
    /**
     * 模型名
     */
	@TableField("MODEL_NAME")
	@ApiModelProperty(required= true,value = "模型名")
	private String modelName;
    /**
     * 決策版本流水
     */
	@TableField("SENCE_VERSIONID")
	@ApiModelProperty(required= true,value = "決策版本流水")
	private String senceVersionid;
    /**
     * 入参
     */
	@TableField("IN_PARAMTER")
	@ApiModelProperty(required= true,value = "入参")
	private String inParamter;
    /**
     * 计算结果
     */
	@TableField("OUT_PARAMTER")
	@ApiModelProperty(required= true,value = "计算结果")
	private String outParamter;
    /**
     * 命中规则总数
     */
	@TableField("EXECUTE_TOTAL")
	@ApiModelProperty(required= true,value = "命中规则总数")
	private Integer executeTotal;
    /**
     * 决策执行类型：0-直接调用，1-模型调用
     */
	@TableField("TYPE")
	@ApiModelProperty(required= true,value = "决策执行类型：0-直接调用，1-模型调用")
	private String type;
    /**
     * 插入时间
     */
	@TableField("CREATE_TIME")
	@ApiModelProperty(required= true,value = "插入时间")
	private Date createTime;

	/**
     * 插入时间
     */
	@TableField("execute_time")
	@ApiModelProperty(required= true,value = "执行时间，毫秒数")
	private Long executeTime;


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

	public Long getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Long executeTime) {
		this.executeTime = executeTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DroolsLog{" +
			"id=" + id +
			", procinstId=" + procinstId +
			", modelName=" + modelName +
			", senceVersionid=" + senceVersionid +
			", inParamter=" + inParamter +
			", outParamter=" + outParamter +
			", executeTotal=" + executeTotal +
			", type=" + type +
			", createTime=" + createTime +
			", executeTime=" + executeTime +
			"}";
	}
}
