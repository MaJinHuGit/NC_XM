package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;

/**
 * <p>Title: HddJq</p> 
 * <p>Package: com.mtlckj.base.jqfx.domain</p>  
 * <p>Description: 黄赌毒警情信息表</p>   
 * <p>Copyright: Copyright (c) 2018</p> 
 * @author majinhu  
 * @date 2018年10月19日   
 * @version 1.0
 */
public class Sys implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String tablename;
	
	private String comments;
	
	private String count;


	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	
}
