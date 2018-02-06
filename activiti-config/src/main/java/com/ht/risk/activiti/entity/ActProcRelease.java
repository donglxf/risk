package com.ht.risk.activiti.entity;

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
 * @since 2018-01-16
 */
@ApiModel
@TableName("act_proc_release")
public class ActProcRelease extends Model<ActProcRelease> {

    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    @TableId("id")
	@ApiModelProperty(required= true,value = "主键")
	private Long id;

    private transient  String idStr;

	/**
	 * 模型定义ID，与 ACT_RE_PROCDEF.ID_ 关联,ACT_RE_PROCDEF 表中有模型部署id
	 */
	@TableField("model_id")
	@ApiModelProperty(required= true,value = "模型ID，与 act_re_model.id_ 关联")
    private String modelId;
    /**
     * 模型定义ID，与 ACT_RE_PROCDEF.ID_ 关联,ACT_RE_PROCDEF 表中有模型部署id
     */
	@TableField("model_procdef_id")
	@ApiModelProperty(required= true,value = "模型定义ID，与 ACT_RE_PROCDEF.ID_ 关联,ACT_RE_PROCDEF 表中有模型部署id")
	private String modelProcdefId;
    /**
     * 模型名称
     */
	@TableField("model_name")
	@ApiModelProperty(required= true,value = "模型名称")
	private String modelName;
    /**
     * 模型版本
     */
	@TableField("model_version")
	@ApiModelProperty(required= true,value = "模型版本")
	private String modelVersion;
    /**
     * 模型分类
     */
	@TableField("model_category")
	@ApiModelProperty(required= true,value = "模型分类")
	private String modelCategory;
    /**
     * 版本类型，0-测试版，1-正式版
     */
	@TableField("version_type")
	@ApiModelProperty(required= true,value = "版本类型，0-测试版，1-正式版")
	private String versionType;
    /**
     * 是否验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;
     */
	@TableField("is_validate")
	@ApiModelProperty(required= true,value = "是否验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;")
	private String isValidate;
    /**
     * 是否审核通过：0-待审核，1-审核通过，2-审核不通过；默认为0;
     */
	@TableField("is_approve")
	@ApiModelProperty(required= true,value = "是否审核通过：0-待审核，1-审核通过，2-审核不通过；默认为0;")
	private String isApprove;
    /**
     * 是否生效：0-有效，1-无效
     */
	@TableField("is_effect")
	@ApiModelProperty(required= true,value = "是否生效：0-有效，1-无效")
	private String isEffect;
    /**
     * 更新时间
     */
	@TableField("update_time")
	@ApiModelProperty(required= true,value = "更新时间")
	private Date updateTime;
    /**
     * 更新用户
     */
	@TableField("update_user")
	@ApiModelProperty(required= true,value = "更新用户")
	private String updateUser;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date createTime;
    /**
     * 创建用户
     */
	@TableField("create_user")
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

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	@Override
	public String toString() {
		return "ActProcRelease{" +
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
