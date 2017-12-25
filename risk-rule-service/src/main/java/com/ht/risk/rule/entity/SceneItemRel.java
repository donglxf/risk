package com.ht.risk.rule.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @author 张鹏
 * @since 2017-12-25
 */
@ApiModel
@TableName("rule_scene_item_rel")
public class SceneItemRel extends Model<SceneItemRel> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	@ApiModelProperty(required= true,value = "")
	private Long id;
    /**
     * 场景id
     */
	@TableField("scene_id")
	@ApiModelProperty(required= true,value = "场景id")
	private Long sceneId;
    /**
     * 变量id
     */
	@TableField("entity_item_id")
	@ApiModelProperty(required= true,value = "变量id")
	private Long entityItemId;
    /**
     * 排序
     */
	@ApiModelProperty(required= true,value = "排序")
	private Integer sort;
	@TableField("cre_time")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date creTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public Long getEntityItemId() {
		return entityItemId;
	}

	public void setEntityItemId(Long entityItemId) {
		this.entityItemId = entityItemId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SceneItemRel{" +
			"id=" + id +
			", sceneId=" + sceneId +
			", entityItemId=" + entityItemId +
			", sort=" + sort +
			", creTime=" + creTime +
			"}";
	}
}
