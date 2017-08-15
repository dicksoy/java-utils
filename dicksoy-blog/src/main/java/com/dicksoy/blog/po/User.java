package com.dicksoy.blog.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.dicksoy.blog.base.BasePo;

@TableName("t_user")
public class User extends BasePo {
	
	private static final long serialVersionUID = 1L;
	
	@TableField("user_name")
	private String username;
	
	private String password;
	
	private Byte gender;
	
	private Date birth;
	
	private String headImage;
	
	private Byte state;
	
	@TableField("user_id")
	private String userId;
	
	@TableField("update_time")
	private Date updateTime;
	
	@TableField("last_login_time")
	private Date lastLoginTime;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Byte getGender() {
		return gender;
	}
	public void setGender(Byte gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public Byte getState() {
		return state;
	}
	public void setState(Byte state) {
		this.state = state;
	}
}
