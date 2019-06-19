package com.mtlckj.base.system.domain;

import java.io.Serializable;
import java.util.Date;

public class ModuleDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sysModuleId;
	private String sysModuleName;
	private String sysModuleCname;
	private String sysModuleText;
	private Date sysModuleCreateTime;
	private Date sysModuleModifyTime;
	private String sysModuleState;
	private Integer sysModuleOrder;
	
	
	public Long getSysModuleId() {
		return sysModuleId;
	}
	public void setSysModuleId(Long sysModuleId) {
		this.sysModuleId = sysModuleId;
	}
	public Integer getSysModuleOrder() {
		return sysModuleOrder;
	}
	public void setSysModuleOrder(Integer sysModuleOrder) {
		this.sysModuleOrder = sysModuleOrder;
	}
	public String getSysModuleName() {
		return sysModuleName;
	}
	public void setSysModuleName(String sysModuleName) {
		this.sysModuleName = sysModuleName;
	}
	public String getSysModuleCname() {
		return sysModuleCname;
	}
	public void setSysModuleCname(String sysModuleCname) {
		this.sysModuleCname = sysModuleCname;
	}
	
	public String getSysModuleText() {
		return sysModuleText;
	}
	public void setSysModuleText(String sysModuleText) {
		this.sysModuleText = sysModuleText;
	}
	
	public Date getSysModuleCreateTime() {
		return sysModuleCreateTime;
	}
	public void setSysModuleCreateTime(Date sysModuleCreateTime) {
		this.sysModuleCreateTime = sysModuleCreateTime;
	}
	public Date getSysModuleModifyTime() {
		return sysModuleModifyTime;
	}
	public void setSysModuleModifyTime(Date sysModuleModifyTime) {
		this.sysModuleModifyTime = sysModuleModifyTime;
	}
	
	public String getSysModuleState() {
		return sysModuleState;
	}
	public void setSysModuleState(String sysModuleState) {
		this.sysModuleState = sysModuleState;
	}
	@Override
	public String toString() {
		return "ModuleDO [sysModuleId=" + sysModuleId + ", sysModuleName=" + sysModuleName + ", sysModuleCname="
				+ sysModuleCname + ", sysModuleText=" + sysModuleText + ", sysModuleCreateTime=" + sysModuleCreateTime
				+ ", sysModuleModifyTime=" + sysModuleModifyTime + ", sysModuleState=" + sysModuleState
				+ ", sysModuleOrder=" + sysModuleOrder + "]";
	}
	
	
	
}
