package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;
/**
 * 字段实体类
 * @author 杨智强
 *
 */

public class ColumnDO implements Serializable {

	// long serialVersionUID :
	private static final long serialVersionUID = 1L;
	
	private String tableName;
	private String columnName;
	private String columnComments;
	private String dataType;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnComments() {
		return columnComments;
	}
	public void setColumnComments(String columnComments) {
		this.columnComments = columnComments;
	}
	
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
