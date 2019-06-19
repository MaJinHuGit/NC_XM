package com.mtlckj.base.jqfx.vo;

import java.util.List;

public class Filter {
	private String condition;
	private String conditionName;
	private List<String> extraParam;
	private String originData;
	private String logicOp;
	private String colName;
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	
	
	public List<String> getExtraParam() {
		return extraParam;
	}
	public void setExtraParam(List<String> extraParam) {
		this.extraParam = extraParam;
	}
	public String getOriginData() {
		return originData;
	}
	public void setOriginData(String originData) {
		this.originData = originData;
	}
	public String getLogicOp() {
		return logicOp;
	}
	public void setLogicOp(String logicOp) {
		this.logicOp = logicOp;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	
	
	
	
}
