package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;

/**
 * <p>Title: HddJq</p> 
 * <p>Package: com.mtlckj.base.jqfx.domain</p>  
 * <p>Description: 四色预警</p>   
 * <p>Copyright: Copyright (c) 2018</p> 
 * @author liangxiao
 * @date 2018年10月24日   
 * @version 1.0
 */
public class Ssyj implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String wfjq; //违法警情
	private String qc; //侵财类
	private String lq; //两抢
	private String rsdq;   //入室盗窃
	private String scdq;   //涉车盗窃
	private String zp;     //诈骗
	private String md;     //摩托车、电动自行车
	
	/************************Getters and Setters**************************/
	
	public String getWfjq() {
		return wfjq;
	}
	public void setWfjq(String wfjq) {
		this.wfjq = wfjq;
	}
	public String getQc() {
		return qc;
	}
	public void setQc(String qc) {
		this.qc = qc;
	}
	public String getLq() {
		return lq;
	}
	public void setLq(String lq) {
		this.lq = lq;
	}
	public String getRsdq() {
		return rsdq;
	}
	public void setRsdq(String rsdq) {
		this.rsdq = rsdq;
	}
	public String getScdq() {
		return scdq;
	}
	public void setScdq(String scdq) {
		this.scdq = scdq;
	}
	public String getZp() {
		return zp;
	}
	public void setZp(String zp) {
		this.zp = zp;
	}
	public String getMd() {
		return md;
	}
	public void setMd(String md) {
		this.md = md;
	}
	
	
	/*********************************************************************/
	
}
