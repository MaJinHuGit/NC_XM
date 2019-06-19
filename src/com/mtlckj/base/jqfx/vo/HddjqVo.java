package com.mtlckj.base.jqfx.vo;

/**
 * <p>Title: HddjqVo</p> 
 * <p>Package: com.mtlckj.base.jqfx.vo</p>  
 * <p>Description: 黄赌毒警情VO</p>   
 * <p>Copyright: Copyright (c) 2018</p> 
 * @author majinhu  
 * @date 2018年10月19日   
 * @version 1.0
 */
public class HddjqVo {
	private String pcsName;//派出所名称
	private String jqs;//警情数
	private Integer wfxs;//未发现数
	private Integer ccs;//查处数
	private String lb;//类别
	private String tb; //同比
	private String tqjqs; //同期警情数量
	
	public String getPcsName() {
		return pcsName;
	}
	public void setPcsName(String pcsName) {
		this.pcsName = pcsName;
	}
	public String getJqs() {
		return jqs;
	}
	public void setJqs(String jqs) {
		this.jqs = jqs;
	}
	public Integer getWfxs() {
		return wfxs;
	}
	public void setWfxs(Integer wfxs) {
		this.wfxs = wfxs;
	}
	public Integer getCcs() {
		return ccs;
	}
	public void setCcs(Integer ccs) {
		this.ccs = ccs;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getTb() {
		return tb;
	}
	public void setTb(String tb) {
		this.tb = tb;
	}
	public String getTqjqs() {
		return tqjqs;
	}
	public void setTqjqs(String tqjqs) {
		this.tqjqs = tqjqs;
	}
}
