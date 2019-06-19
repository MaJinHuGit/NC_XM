package com.mtlckj.base.jqfx.vo;

import java.io.Serializable;

/**
 * 辖区类别，包含辖区中的各个类别
 * @author sunny
 *
 */
public class Xqajlb implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ZsFourMOY zp;
	private ZsFourMOY qc;
	private  ZsFourMOY lq;
	private ZsFourMOY rsdq;
	private ZsFourMOY scdq;
	private ZsFourMOY md;
	private ZsFourMOY wfjq;
	private String mapColor;
	
	
	
	public Xqajlb() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Xqajlb(ZsFourMOY zp, ZsFourMOY qc, ZsFourMOY lq, ZsFourMOY rsdq, ZsFourMOY scdq, ZsFourMOY md,
			ZsFourMOY wfjq, String mapColor) {
		super();
		this.zp = zp;
		this.qc = qc;
		this.lq = lq;
		this.rsdq = rsdq;
		this.scdq = scdq;
		this.md = md;
		this.wfjq = wfjq;
		this.mapColor = mapColor;
	}



	public ZsFourMOY getZp() {
		return zp;
	}



	public void setZp(ZsFourMOY zp) {
		this.zp = zp;
	}



	public ZsFourMOY getQc() {
		return qc;
	}



	public void setQc(ZsFourMOY qc) {
		this.qc = qc;
	}



	public ZsFourMOY getLq() {
		return lq;
	}



	public void setLq(ZsFourMOY lq) {
		this.lq = lq;
	}



	public ZsFourMOY getRsdq() {
		return rsdq;
	}



	public void setRsdq(ZsFourMOY rsdq) {
		this.rsdq = rsdq;
	}



	public ZsFourMOY getScdq() {
		return scdq;
	}



	public void setScdq(ZsFourMOY scdq) {
		this.scdq = scdq;
	}



	public ZsFourMOY getMd() {
		return md;
	}



	public void setMd(ZsFourMOY md) {
		this.md = md;
	}



	public ZsFourMOY getWfjq() {
		return wfjq;
	}



	public void setWfjq(ZsFourMOY wfjq) {
		this.wfjq = wfjq;
	}



	public String getMapColor() {
		return mapColor;
	}



	public void setMapColor(String mapColor) {
		this.mapColor = mapColor;
	}



	/**
	 * 根据类别超过的数量，获取到类别
	 * @return
	 */
	public void setXqMapColor(){
		int count=0;
		if(this.zp.compare())
			count++;
		if(this.qc.compare())
			count++;
		if(this.lq.compare())
			count++;
		if(this.rsdq.compare())
			count++;
		if(this.scdq.compare())
			count++;
		if(this.md.compare())
			count++;
		if(this.wfjq.compare())
			count++;
		if(count == 5){
			this.mapColor = "#FFFF00";
		}else if(count == 6){
			this.mapColor = "#FF9900";
		}else if(count == 7){
			this.mapColor = "#FF0000";
		}else{
			this.mapColor = "#00FF00";
		}
	}
	
}
