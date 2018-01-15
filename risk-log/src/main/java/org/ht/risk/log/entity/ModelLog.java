package org.ht.risk.log.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.sql.Blob;
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
 * @since 2018-01-10
 */
@ApiModel
@TableName("RISK_MODEL_LOG")
public class ModelLog extends Model<ModelLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,流程运行ID
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键,流程运行ID")
	private String id;
    /**
     * 模型发布流水号
     */
	@TableField("RELEASE_ID")
	@ApiModelProperty(required= true,value = "模型发布流水号")
	private String releaseId;
    /**
     * 调用方式；0 数据传入调用、1 通过变量绑定数据库信息获取数据
     */
	@TableField("CALLTYPE")
	@ApiModelProperty(required= true,value = "调用方式；0 数据传入调用、1 通过变量绑定数据库信息获取数据")
	private String calltype;
    /**
     * 入参
     */
	@TableField("IN_PARAMTER")
	@ApiModelProperty(required= true,value = "入参")
	private String inParamter;
    /**
     * 出参
     */
	@TableField("OUT_PARAMTER")
	@ApiModelProperty(required= true,value = "出参")
	private String outParamter;
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

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public String getCalltype() {
		return calltype;
	}

	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}

	public String getInParamter() {
		return inParamter;
	}

	public void setInParamter(String inParamter) {
		this.inParamter = inParamter;
	}

	public String getOutParamter() {
		return outParamter;
	}

	public void setOutParamter(String outParamter) {
		this.outParamter = outParamter;
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
		return "ModelLog{" +
			"id=" + id +
			", releaseId=" + releaseId +
			", calltype=" + calltype +
			", inParamter=" + inParamter +
			", outParamter=" + outParamter +
			", createTime=" + createTime +
			", createUser=" + createUser +
			"}";
	}
}
