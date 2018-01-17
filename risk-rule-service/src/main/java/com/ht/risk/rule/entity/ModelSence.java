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
@TableName("RISK_MODEL_SENCE")
public class ModelSence extends Model<ModelSence> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键")
	private Long id;
    /**
     * 模型定义ID
     */
	@TableField("MODEL_PROCDEF_ID")
	@ApiModelProperty(required= true,value = "模型定义ID")
	private String modelProcdefId;
    /**
     * 決策版本流水号
     */
	@TableField("SENCE_VERSION_ID")
	@ApiModelProperty(required= true,value = "決策版本流水号")
	private Long senceVersionId;
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

	public String getModelProcdefId() {
		return modelProcdefId;
	}

	public void setModelProcdefId(String modelProcdefId) {
		this.modelProcdefId = modelProcdefId;
	}

	public Long getSenceVersionId() {
		return senceVersionId;
	}

	public void setSenceVersionId(Long senceVersionId) {
		this.senceVersionId = senceVersionId;
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
		return "ModelSence{" +
			"id=" + id +
			", modelProcdefId=" + modelProcdefId +
			", senceVersionId=" + senceVersionId +
			", isEffect=" + isEffect +
			", createTime=" + createTime +
			", createUser=" + createUser +
			"}";
	}
}
