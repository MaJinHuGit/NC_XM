package com.mtlckj.base.jqfx.vo;

import java.math.BigDecimal;

/**
 * 案件类别，包含当周的案件总数，浮动比率，可浮动比率，浮动比率是否超过可浮动比率
 * @author sunny
 *
 */
public class Ajlb {
	private Integer ajzs;
	private BigDecimal fdbv;
	private BigDecimal kfdbv;
	private boolean over;
	
	public Ajlb(Integer ajzs, BigDecimal fdbv, BigDecimal kfdbv) {
		this.ajzs = ajzs;
		this.fdbv = fdbv;
		this.kfdbv = kfdbv;
		this.over = false;
	}
	public Integer getAjzs() {
		return ajzs;
	}
	public void setAjzs(Integer ajzs) {
		this.ajzs = ajzs;
	}
	public BigDecimal getFdbv() {
		return fdbv;
	}
	public void setFdbv(BigDecimal fdbv) {
		this.fdbv = fdbv;
	}
	public BigDecimal getKfdbv() {
		return kfdbv;
	}
	public void setKfdbv(BigDecimal kfdbv) {
		this.kfdbv = kfdbv;
	}
	public boolean isOver() {
		return over;
	}
	public void setOver(boolean over) {
		this.over = over;
	}
	
	
	public boolean compare(){
		if(this.fdbv.compareTo(this.fdbv)==1){
			this.over=true;
		}
		return this.over;
	}
	
}
