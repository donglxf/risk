package com.ht.risk.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dyb
 * @since 2018-01-18
 */
public class TestDroolsDetailLog  {



	private Long droolsLogid;
    /**
     * 命中的规则
     */
	private String executeRulename;


	public Long getDroolsLogid() {
		return droolsLogid;
	}

	public void setDroolsLogid(Long droolsLogid) {
		this.droolsLogid = droolsLogid;
	}

	public String getExecuteRulename() {
		return executeRulename;
	}

	public void setExecuteRulename(String executeRulename) {
		this.executeRulename = executeRulename;
	}

}
