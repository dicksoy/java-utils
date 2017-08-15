package com.dicksoy.blog.po;

import com.baomidou.mybatisplus.annotations.TableName;
import com.dicksoy.blog.base.BasePo;

@TableName("t_article")
public class Article extends BasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	
	private String description;
	
	private Byte type;
	
	private Byte sort;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Byte getSort() {
		return sort;
	}
	public void setSort(Byte sort) {
		this.sort = sort;
	}
}
