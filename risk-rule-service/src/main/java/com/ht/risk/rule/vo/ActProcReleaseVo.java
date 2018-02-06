package com.ht.risk.rule.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.ht.risk.rule.entity.ModelSence;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ActProcReleaseVo implements Serializable{
    /**
     * 主键
     */
    private String id;
    /**
     * 模型定义ID，与 ACT_RE_PROCDEF.ID_ 关联,ACT_RE_PROCDEF 表中有模型部署id
     */
    private String modelProcdefId;
    /**
     * 模型名称
     */
    private String modelName;
    /**
     * 模型版本
     */
    private String modelVersion;
    /**
     * 模型分类
     */
    private String modelCategory;
    /**
     * 版本类型，0-测试版，1-正式版
     */
    private String versionType;
    /**
     * 是否验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;
     */
    private String isValidate;
    /**
     * 是否审核通过：0-待审核，1-审核通过，2-审核不通过；默认为0;
     */
    private String isApprove;
    /**
     * 是否生效：0-有效，1-无效
     */
    private String isEffect;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新用户
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 返回结果用
     */
    @TableField(exist = false)
    private List<ModelSence> variableMap;

    public List<ModelSence> getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(List<ModelSence> variableMap) {
        this.variableMap = variableMap;
    }

    private String taskId;

    private String taskStatus;

    private String cornText;

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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCornText() {
        return cornText;
    }

    public void setCornText(String cornText) {
        this.cornText = cornText;
    }

}
