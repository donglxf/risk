/*
 * 文件名 PageResult.java
 * 版权 Copyright 2017 团贷网
 * 创建人 谭荣巧
 * 创建时间 2017年11月20日 下午3:40:50
 */
package com.ht.risk.common.result;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Rest接口请求结果对象<br>
 *
 * @author 谭荣巧
 * @date 2017年11月20日 下午3:40:50
 * @since cams-common 1.0-SNAPSHOT
 */
@ApiModel
public class PageResult<T> {


	/**

		 * 错误代码
		 */
		@ApiModelProperty(required = true, value = "错误代码")
		private int code;

		private int count;
		/**
		 * 错误描述
		 */
		@ApiModelProperty(required = true, value = "错误描述")
		private String msg;
		/**
		 * 传递给请求者的数据
		 */
		@ApiModelProperty(value = "传递给请求者的数据")
		private T data;





	public PageResult() {
		super();
	}

		/**
		 * 构造请求成功的结果对象<br>
		 *
		 * @author 谭荣巧
		 * @date 2017年11月20日 下午3:47:10
		 * @return 请求的结果对象
		 * @since cams-common 1.0-SNAPSHOT
		 */
		public static <T> PageResult<T> success(int count) {
		PageResult<T> PageResult = new PageResult<T>();
		PageResult.setCode(0);
		PageResult.setMsg("请求成功！");
		return PageResult;
	}

		/**
		 * 构造请求成功的结果对象<br>
		 *
		 * @author 谭荣巧
		 * @date 2017年11月20日 下午3:47:36
		 * @param data
		 *            返回的数据集
		 * @return 请求的结果对象
		 * @since cams-common 1.0-SNAPSHOT
		 */
		public static <T> PageResult<T> success(T data,int count) {
		PageResult<T> PageResult = new PageResult<T>();
		PageResult.setCode(0);
		PageResult.setCount(count);
		PageResult.setMsg("请求成功！");
		PageResult.setData(data);
		return PageResult;
	}

		/**
		 * 构造请求失败的结果对象<br>
		 *
		 * @author 谭荣巧
		 * @date 2017年11月20日 下午3:48:01
		 * @param code
		 *            结果代码
		 * @param msg
		 *            结果描述
		 * @return 请求的结果对象
		 * @since cams-common 1.0-SNAPSHOT
		 */
		public static <T> PageResult<T> error(int code, String msg) {
			PageResult<T> PageResult = new PageResult<T>();
		PageResult.setCode(code);
		PageResult.setMsg(msg);
		return PageResult;
	}

		/**
		 * 构造结果对象<br>
		 *
		 * @author 谭荣巧
		 * @date 2017年11月20日 下午3:48:01
		 * @param code
		 *            结果代码
		 * @param msg
		 *            结果描述
		 * @param data
		 *            返回的数据集
		 * @return 请求的结果对象
		 * @since cams-common 1.0-SNAPSHOT
		 */
		public static <T> PageResult<T> build(int code, String msg, T data) {
			PageResult<T> PageResult = new PageResult<T>();
		PageResult.setCode(code);
		PageResult.setMsg(msg);
		PageResult.setData(data);
		return PageResult;
	}

		/**
		 * @return the code
		 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
