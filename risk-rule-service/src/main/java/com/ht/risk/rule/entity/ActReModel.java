package com.ht.risk.rule.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2017-12-19
 */
@ApiModel
@TableName("act_re_model")
public class ActReModel extends Model<ActReModel> {

    private static final long serialVersionUID = 1L;

    @TableId("id_")
	@ApiModelProperty(required= true,value = "")
	private String id;
	@TableField("rev_")
	@ApiModelProperty(required= true,value = "")
	private Integer rev;
	@TableField("name_")
	@ApiModelProperty(required= true,value = "")
	private String name;
	@TableField("key_")
	@ApiModelProperty(required= true,value = "")
	private String key;
	@TableField("category_")
	@ApiModelProperty(required= true,value = "")
	private String category;
	@TableField("create_time_")
	@ApiModelProperty(required= true,value = "")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createTime;
	@TableField("last_update_time_")
	@ApiModelProperty(required= true,value = "")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date lastUpdateTime;
	@TableField("version_")
	@ApiModelProperty(required= true,value = "")
	private Integer version;
	@TableField("meta_info_")
	@ApiModelProperty(required= true,value = "")
	private String metaInfo;
	@TableField("deployment_id_")
	@ApiModelProperty(required= true,value = "")
	private String deploymentId;
	@TableField("editor_source_value_id_")
	@ApiModelProperty(required= true,value = "")
	private String editorSourceValueId;
	@TableField("editor_source_extra_value_id_")
	@ApiModelProperty(required= true,value = "")
	private String editorSourceExtraValueId;
	@TableField("tenant_id_")
	@ApiModelProperty(required= true,value = "")
	private String tenantId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRev() {
		return rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getEditorSourceValueId() {
		return editorSourceValueId;
	}

	public void setEditorSourceValueId(String editorSourceValueId) {
		this.editorSourceValueId = editorSourceValueId;
	}

	public String getEditorSourceExtraValueId() {
		return editorSourceExtraValueId;
	}

	public void setEditorSourceExtraValueId(String editorSourceExtraValueId) {
		this.editorSourceExtraValueId = editorSourceExtraValueId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ActReModel{" +
			"id=" + id +
			", rev=" + rev +
			", name=" + name +
			", key=" + key +
			", category=" + category +
			", createTime=" + createTime +
			", lastUpdateTime=" + lastUpdateTime +
			", version=" + version +
			", metaInfo=" + metaInfo +
			", deploymentId=" + deploymentId +
			", editorSourceValueId=" + editorSourceValueId +
			", editorSourceExtraValueId=" + editorSourceExtraValueId +
			", tenantId=" + tenantId +
			"}";
	}
}
