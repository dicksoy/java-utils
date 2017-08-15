package com.dicksoy.blog.base;

import java.io.Serializable;

public class BaseResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* 成功与否 */
	private boolean success;
	/* 错误码 */
	private Integer code;
	/* 提示信息 */
	private String msg;
	/* 数据 */
	private Object data;

	public static class Builder {
		
		private final boolean success;
		private final Integer code;
		
		private String msg;
		private Object data;
		
		public Builder(boolean success, Integer code) {
			this.success = success;
			this.code = code;
		}
		
		public Builder msg(String val) {
			msg = val;
			return this;
		}
		
		public Builder data(Object val) {
			data = val;
			return this;
		}
		
		public BaseResult build() {
			return new BaseResult(this);
		}
		
 		/** 枚举方式
		public Builder(Enums enums) {
			this.success = enums.getCode() == 200 ? true : false; 
			this.code = enums.getCode();
		}
	
		public BaseResult build(Enums enums) {
			return new BaseResult(enums);
		}*/
	}
	
	public BaseResult(Builder builder) {
		success = builder.success;
		code = builder.code;
		msg = builder.msg;
		data = builder.data;
	}

	public boolean isSuccess() {
		return success;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}
}
