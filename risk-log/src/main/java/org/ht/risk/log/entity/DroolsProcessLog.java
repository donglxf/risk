package org.ht.risk.log.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
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
 * @since 2018-01-05
 */
@ApiModel
@TableName("drools_process_log")
public class DroolsProcessLog extends Model<DroolsProcessLog> {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(required= true,value = "")
	private Long id;
	@ApiModelProperty(required= true,value = "")
	private Long droolsid;
    /**
     * 命中的规则
     */
	@TableField("executerule")
	@ApiModelProperty(required= true,value = "命中的规则")
	private String executeRule;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDroolsid() {
		return droolsid;
	}

	public void setDroolsid(Long droolsid) {
		this.droolsid = droolsid;
	}

	public String getExecuteRule() {
		return executeRule;
	}

	public void setExecuteRule(String executeRule) {
		this.executeRule = executeRule;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DroolsProcessLog{" +
			"id=" + id +
			", droolsid=" + droolsid +
			", executerule=" + executeRule +
			"}";
	}
}
