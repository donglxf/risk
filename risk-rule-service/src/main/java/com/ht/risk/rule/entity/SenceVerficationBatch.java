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
 * @author dyb
 * @since 2018-01-22
 */
@ApiModel
@TableName("RISK_SENCE_VERFICATION_BATCH")
public class SenceVerficationBatch extends Model<SenceVerficationBatch> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,批次号
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键,批次号")
	private Long id;
    /**
     * 決策版本流水
     */
	@TableField("SENCE_VERSION_ID")
	@ApiModelProperty(required= true,value = "決策版本流水")
	private String senceVersionId;
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenceVersionId() {
		return senceVersionId;
	}

	public void setSenceVersionId(String senceVersionId) {
		this.senceVersionId = senceVersionId;
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
		return "SenceVerficationBatch{" +
			"id=" + id +
			", senceVersionId=" + senceVersionId +
			", batchSize=" + batchSize +
			", isEffect=" + isEffect +
			", createTime=" + createTime +
			", createUser=" + createUser +
			"}";
	}
}
