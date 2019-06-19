package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;

/**
 * 资源表的基本信息
 * @author 杨智强
 *
 */

public class TableDO implements Serializable{
	String tableName;
	String comments;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
