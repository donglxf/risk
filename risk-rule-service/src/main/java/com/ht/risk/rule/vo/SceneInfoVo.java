package com.ht.risk.rule.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.ht.risk.rule.entity.SceneInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author dyb
 * @since 2018-1-27
 */
@ApiModel
public class SceneInfoVo extends SceneInfo {

	private String name;
	private String value;


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
