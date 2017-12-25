package com.ht.risk.rule.entity;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 规则引擎实体信息表
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@ApiModel
@TableName("rule_entity_info")
public class EntityInfo extends Model<EntityInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("entity_id")
    @ApiModelProperty(required = true, value = "主键")
    private Long entityId;
    /**
     * 名称
     */
    @TableField("entity_name")
    @ApiModelProperty(required = true, value = "名称")
    private String entityName;
    /**
     * 描述
     */
    @TableField("entity_desc")
    @ApiModelProperty(required = true, value = "描述")
    private String entityDesc;
    /**
     * 标识
     */
    @TableField("entity_identify")
    @ApiModelProperty(required = true, value = "标识")
    private String entityIdentify;
    /**
     * 包路径
     */
    @TableField("pkg_name")
    @ApiModelProperty(required = true, value = "包路径")
    private String pkgName;
    /**
     * 创建人
     */
    @TableField("cre_user_id")
    @ApiModelProperty(required = true, value = "创建人")
    private Long creUserId;
    /**
     * 创建时间
     */
    @TableField("cre_time")
    @ApiModelProperty(required = true, value = "创建时间")
    private Date creTime;
    /**
     * 是否有效(1是0否)
     */
    @TableField("is_effect")
    @ApiModelProperty(required = true, value = "是否有效(1是0否)")
    private Integer isEffect;
    /**
     * 备注
     */
    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    @TableField(exist=false)
    @ApiModelProperty(required = false, value = "变量集合")
    private List<EntityItemInfo> items;


    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityDesc() {
        return entityDesc;
    }

    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc;
    }

    public String getEntityIdentify() {
        return entityIdentify;
    }

    public void setEntityIdentify(String entityIdentify) {
        this.entityIdentify = entityIdentify;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Long getCreUserId() {
        return creUserId;
    }

    public void setCreUserId(Long creUserId) {
        this.creUserId = creUserId;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public Integer getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(Integer isEffect) {
        this.isEffect = isEffect;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<EntityItemInfo> getItems() {
        return items;
    }

    public void setItems(List<EntityItemInfo> items) {
        this.items = items;
    }

    @Override
    protected Serializable pkVal() {
        return this.entityId;
    }

    @Override
    public String toString() {
        return "EntityInfo{" +
                "entityId=" + entityId +
                ", entityName=" + entityName +
                ", entityDesc=" + entityDesc +
                ", entityIdentify=" + entityIdentify +
                ", pkgName=" + pkgName +
                ", creUserId=" + creUserId +
                ", creTime=" + creTime +
                ", isEffect=" + isEffect +
                ", remark=" + remark +
                "}";
    }

    /**
     * 方法说明:获取实体类名称
     */
    public String getEntityClazz() {
       // int index = pkgName.lastIndexOf(".");
      //  return pkgName.substring(index + 1);
        return "11";
    }
}
