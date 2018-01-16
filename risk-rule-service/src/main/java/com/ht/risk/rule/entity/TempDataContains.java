package com.ht.risk.rule.entity;

import java.io.Serializable;

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
 * @author dyb
 * @since 2018-01-15
 */
@ApiModel
@TableName("TEMP_DATA_CONTAINS")
public class TempDataContains extends Model<TempDataContains> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@ApiModelProperty(required= true,value = "主键")
	private Long id;
    /**
     * 规则id
     */
	@TableField("scene_id")
	@ApiModelProperty(required= true,value = "规则id")
	private Long sceneId;
    /**
     * 信息
     */
	@ApiModelProperty(required= true,value = "信息")
	private String message;
    /**
     * 消费金额
     */
	@ApiModelProperty(required= true,value = "消费金额")
	private String amount;
    /**
     * 积分
     */
	@ApiModelProperty(required= true,value = "积分")
	private String score;
    /**
     * 年龄
     */
	@ApiModelProperty(required= true,value = "年龄")
	private String age;
    /**
     * 性别
     */
	@ApiModelProperty(required= true,value = "性别")
	private String sex;
    /**
     * 学历
     */
	@ApiModelProperty(required= true,value = "学历")
	private String xl;
    /**
     * 婚姻状态
     */
	@ApiModelProperty(required= true,value = "婚姻状态")
	private String marry;
    /**
     * 抵押物类型
     */
	@ApiModelProperty(required= true,value = "抵押物类型")
	private String type;
    /**
     * 楼龄
     */
	@TableField("hourseAge")
	@ApiModelProperty(required= true,value = "楼龄")
	private String hourseAge;
    /**
     * 户籍
     */
	@ApiModelProperty(required= true,value = "户籍")
	private String address;
    /**
     * 名称
     */
	@TableField("student_name")
	@ApiModelProperty(required= true,value = "名称")
	private String studentName;


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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getMarry() {
		return marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHourseAge() {
		return hourseAge;
	}

	public void setHourseAge(String hourseAge) {
		this.hourseAge = hourseAge;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DataContains{" +
			"id=" + id +
			", sceneId=" + sceneId +
			", message=" + message +
			", amount=" + amount +
			", score=" + score +
			", age=" + age +
			", sex=" + sex +
			", xl=" + xl +
			", marry=" + marry +
			", type=" + type +
			", hourseAge=" + hourseAge +
			", address=" + address +
			", studentName=" + studentName +
			"}";
	}
}
