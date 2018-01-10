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
 * @since 2018-01-10
 */
@ApiModel
@TableName("risk_model_release")
public class ModelRelease extends Model<ModelRelease> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键")
	private String id;
    /**
     * 模型定义ID，与 ACT_RE_PROCDEF.ID_ 关联
     */
	@TableField("MODEL_PROCDEF_ID")
	@ApiModelProperty(required= true,value = "模型定义ID，与 ACT_RE_PROCDEF.ID_ 关联")
	private String modelProcdefId;
    /**
     * 模型名称
     */
	@TableField("MODEL_NAME")
	@ApiModelProperty(required= true,value = "模型名称")
	private String modelName;
    /**
     * 模型版本
     */
	@TableField("MODEL_VERSION")
	@ApiModelProperty(required= true,value = "模型版本")
	private String modelVersion;
    /**
     * 模型分类
     */
	@TableField("MODEL_CATEGORY")
	@ApiModelProperty(required= true,value = "模型分类")
	private String modelCategory;
    /**
     * 版本类型，0-测试版，1-正式版
     */
	@TableField("VERSION_TYPE")
	@ApiModelProperty(required= true,value = "版本类型，0-测试版，1-正式版")
	private String versionType;
    /**
     * 是否验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;
     */
	@TableField("IS_VALIDATE")
	@ApiModelProperty(required= true,value = "是否验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;")
	private String isValidate;
    /**
     * 是否审核通过：0-待审核，1-审核通过，2-审核不通过；默认为0;
     */
	@TableField("IS_APPROVE")
	@ApiModelProperty(required= true,value = "是否审核通过：0-待审核，1-审核通过，2-审核不通过；默认为0;")
	private String isApprove;
    /**
     * 是否生效：0-有效，1-无效
     */
	@TableField("IS_EFFECT")
	@ApiModelProperty(required= true,value = "是否生效：0-有效，1-无效")
	private String isEffect;
    /**
     * 更新时间
     */
	@TableField("UPDATE_TIME")
	@ApiModelProperty(required= true,value = "更新时间")
	private Date updateTime;
    /**
     * 更新用户
     */
	@TableField("UPDATE_USER")
	@ApiModelProperty(required= true,value = "更新用户")
	private String updateUser;
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

	public String getModelProcdefId() {
		return modelProcdefId;
	}

	public void setModelProcdefId(String modelProcdefId) {
		this.modelProcdefId = modelProcdefId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}

	public String getModelCategory() {
		return modelCategory;
	}

	public void setModelCategory(String modelCategory) {
		this.modelCategory = modelCategory;
	}

	public String getVersionType() {
		return versionType;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}

	public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}

	public String getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}

	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ModelRelease{" +
			"id=" + id +
			", modelProcdefId=" + modelProcdefId +
			", modelName=" + modelName +
			", modelVersion=" + modelVersion +
			", modelCategory=" + modelCategory +
			", versionType=" + versionType +
			", isValidate=" + isValidate +
			", isApprove=" + isApprove +
			", isEffect=" + isEffect +
			", updateTime=" + updateTime +
			", updateUser=" + updateUser +
			", createTime=" + createTime +
			", createUser=" + createUser +
			"}";
	}
}
