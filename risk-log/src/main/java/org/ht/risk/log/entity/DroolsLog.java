package org.ht.risk.log.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 
 * </p>
 *
 * @author dyb
 * @since 2018-01-05
 */
@ApiModel
@TableName("drools_log")
public class DroolsLog extends Model<DroolsLog> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
	@ApiModelProperty(required= true,value = "")
	private Long id;
    /**
     * 模型实例id
     */
	@TableField("proc_inst_id")
	@ApiModelProperty(required= true,value = "模型实例id")
	private Long procInstId;
    /**
     * 场景id
     */
	@TableField("scene_id")
	@ApiModelProperty(required= true,value = "场景id")
	private String sceneId;
    /**
     * 插入时间
     */
	@TableField("cre_time")
	@ApiModelProperty(required= true,value = "插入时间")
	private Date creTime;
    /**
     * 规则版本
     */
	@TableField("droolsversion")
	@ApiModelProperty(required= true,value = "规则版本")
	private String droolsVersion;
    /**
     * 入参
     */
	@TableField("in_param")
	@ApiModelProperty(required= true,value = "入参")
	private String inParam;
    /**
     * 计算结果
     */
	@ApiModelProperty(required= true,value = "计算结果")
	private String result;
    /**
     * 命中数目
     */
	@TableField("execute_total")
	@ApiModelProperty(required= true,value = "命中数目")
	private Integer executeTotal;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(Long procInstId) {
		this.procInstId = procInstId;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public String getDroolsVersion() {
		return droolsVersion;
	}

	public void setDroolsVersion(String droolsVersion) {
		this.droolsVersion = droolsVersion;
	}

	public String getInParam() {
		return inParam;
	}

	public void setInParam(String inParam) {
		this.inParam = inParam;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getExecuteTotal() {
		return executeTotal;
	}

	public void setExecuteTotal(Integer executeTotal) {
		this.executeTotal = executeTotal;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DroolsLog{" +
			"id=" + id +
			", procInstId=" + procInstId +
			", sceneId=" + sceneId +
			", creTime=" + creTime +
			", droolsversion=" + droolsVersion +
			", inParam=" + inParam +
			", result=" + result +
			", executeTotal=" + executeTotal +
			"}";
	}
}
