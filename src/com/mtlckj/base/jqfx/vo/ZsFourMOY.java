package com.mtlckj.base.jqfx.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 本周总数，去年同月总数，
 * @author sunny
 *
 */
public class ZsFourMOY implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 周总数
	 */
	private Integer zzs;
	/**
	 * 前一年一个月总数
	 */
	private Integer lastFirstYearMonthzs;
	/**
	 * 前第二年月总数
	 */
	private Integer lastSecondYearMonthzs;
	
	/**
	 * 前第三年月总数
	 */
	private Integer lastThirdYearMonthzs;
	/**
	 * 前第一年的周常量
	 */
	private BigDecimal lastFirstYearMonthCount;
	
	/**
	 * 前第二年的周常量
	 */
	private BigDecimal lastSecondYearMonthCount;
	/**
	 * 前第三年的周常量
	 */
	private BigDecimal lastThirdYearMonthCount;
	/**
	 * 前第一年时间差
	 */
	private Integer lastFirstYearDays;
	
	/**
	 * 前第二年时间差
	 */
	private Integer lastSecondYearDays;
	
	/**
	 * 前第三年时间差
	 */
	private Integer lastThirdYearDays;
	
	/**
	 * 浮动比率
	 */
	private BigDecimal fdbv;
	/**
	 * 可浮动比率
	 */
	private BigDecimal kfdbv;
	
	private boolean over;
	
	public ZsFourMOY() {
		this.over=false;
	}
	public ZsFourMOY(Integer zzs, Integer lastFirstYearMonthzs, Integer lastSecondYearMonthzs,
			Integer lastThirdYearMonthzs) {
		this.zzs = zzs;
		this.lastFirstYearMonthzs = lastFirstYearMonthzs;
		this.lastSecondYearMonthzs = lastSecondYearMonthzs;
		this.lastThirdYearMonthzs = lastThirdYearMonthzs;
		this.over=false;
	}
	public Integer getZzs() {
		return zzs;
	}
	public void setZzs(Integer zzs) {
		this.zzs = zzs;
	}
	public Integer getLastFirstYearMonthzs() {
		return lastFirstYearMonthzs;
	}
	public void setLastFirstYearMonthzs(Integer lastFirstYearMonthzs) {
		this.lastFirstYearMonthzs = lastFirstYearMonthzs;
	}
	public Integer getLastSecondYearMonthzs() {
		return lastSecondYearMonthzs;
	}
	public void setLastSecondYearMonthzs(Integer lastSecondYearMonthzs) {
		this.lastSecondYearMonthzs = lastSecondYearMonthzs;
	}
	public Integer getLastThirdYearMonthzs() {
		return lastThirdYearMonthzs;
	}
	public void setLastThirdYearMonthzs(Integer lastThirdYearMonthzs) {
		this.lastThirdYearMonthzs = lastThirdYearMonthzs;
	}
	
	public BigDecimal getLastFirstYearMonthCount() {
		return lastFirstYearMonthCount;
	}
	public void setLastFirstYearMonthCount(BigDecimal lastFirstYearMonthCount) {
		this.lastFirstYearMonthCount = lastFirstYearMonthCount;
	}
	public BigDecimal getLastSecondYearMonthCount() {
		return lastSecondYearMonthCount;
	}
	public void setLastSecondYearMonthCount(BigDecimal lastSecondYearMonthCount) {
		this.lastSecondYearMonthCount = lastSecondYearMonthCount;
	}
	public BigDecimal getLastThirdYearMonthCount() {
		return lastThirdYearMonthCount;
	}
	public void setLastThirdYearMonthCount(BigDecimal lastThirdYearMonthCount) {
		this.lastThirdYearMonthCount = lastThirdYearMonthCount;
	}
	public Integer getLastFirstYearDays() {
		return lastFirstYearDays;
	}
	public void setLastFirstYearDays(Integer lastFirstYearDays) {
		this.lastFirstYearDays = lastFirstYearDays;
	}
	public Integer getLastSecondYearDays() {
		return lastSecondYearDays;
	}
	public void setLastSecondYearDays(Integer lastSecondYearDays) {
		this.lastSecondYearDays = lastSecondYearDays;
	}
	public Integer getLastThirdYearDays() {
		return lastThirdYearDays;
	}
	public void setLastThirdYearDays(Integer lastThirdYearDays) {
		this.lastThirdYearDays = lastThirdYearDays;
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
	public void setThreeYearZcl(){
		
		BigDecimal lastFirstYearMonthzsBigDecimal=new BigDecimal(this.lastFirstYearMonthzs);
		BigDecimal lastFirstYearDaysBigDecimal=new BigDecimal(this.lastFirstYearDays);
		
		BigDecimal lastSecondYearMonthzsBigDecimal=new BigDecimal(this.lastSecondYearMonthzs);
		BigDecimal lastSecondYearDaysBigDecimal=new BigDecimal(this.lastSecondYearDays);
		
		BigDecimal lastThirdYearMonthzsBigDecimal=new BigDecimal(this.lastThirdYearMonthzs);
		BigDecimal lastThirdYearDaysBigDecimal=new BigDecimal(this.lastThirdYearDays);
		
		this.lastFirstYearMonthCount=(lastFirstYearMonthzsBigDecimal.divide(lastFirstYearDaysBigDecimal,4,BigDecimal.ROUND_HALF_DOWN)).multiply(BigDecimal.valueOf(7));
		
		this.lastSecondYearMonthCount=(lastSecondYearMonthzsBigDecimal.divide(lastSecondYearDaysBigDecimal,4,BigDecimal.ROUND_HALF_DOWN)).multiply(BigDecimal.valueOf(7));

		this.lastThirdYearMonthCount=(lastThirdYearMonthzsBigDecimal.divide(lastThirdYearDaysBigDecimal ,4,BigDecimal.ROUND_HALF_DOWN)).multiply(BigDecimal.valueOf(7));
		
	}
	
	public void setFdbvAndKfdbv(){
		BigDecimal lastSecondAndThirdZclFdbv = BigDecimal.ZERO;
		BigDecimal lastFirstdAndSecondZclFdbv = BigDecimal.ZERO;
		/**
		 * 计算可浮动比率
		 */
		if(this.lastThirdYearMonthCount.compareTo(BigDecimal.ZERO)==0){
			lastSecondAndThirdZclFdbv = (this.lastSecondYearMonthCount.subtract(this.lastThirdYearMonthCount));	
		}else{
			lastSecondAndThirdZclFdbv= (this.lastSecondYearMonthCount.subtract(this.lastThirdYearMonthCount)).divide(this.lastThirdYearMonthCount,4,BigDecimal.ROUND_CEILING);
		}
		
		if(this.lastSecondYearMonthCount.compareTo(BigDecimal.ZERO)==0){
			lastFirstdAndSecondZclFdbv= (this.lastFirstYearMonthCount.subtract(this.lastSecondYearMonthCount));
		}else{
			lastFirstdAndSecondZclFdbv= (this.lastFirstYearMonthCount.subtract(this.lastSecondYearMonthCount)).divide(this.lastSecondYearMonthCount,4,BigDecimal.ROUND_CEILING);
		}
		this.kfdbv=(lastSecondAndThirdZclFdbv.add(lastFirstdAndSecondZclFdbv)).divide(BigDecimal.valueOf(2),4,BigDecimal.ROUND_CEILING).multiply(BigDecimal.valueOf(100));
		if(this.kfdbv.compareTo(BigDecimal.ZERO)==-1){
			this.kfdbv=BigDecimal.ZERO;
		}
		
		/**
		 * 计算浮动比率
		 */
		if(this.lastFirstYearMonthCount.compareTo(BigDecimal.ZERO)==0){
			this.fdbv=(BigDecimal.valueOf(this.zzs).subtract(this.lastFirstYearMonthCount)).multiply(BigDecimal.valueOf(100));
		}else{
			this.fdbv=(BigDecimal.valueOf(this.zzs).subtract(this.lastFirstYearMonthCount)).divide(this.lastFirstYearMonthCount,4,BigDecimal.ROUND_CEILING).multiply(BigDecimal.valueOf(100));
		}
		
		if(this.fdbv.compareTo(BigDecimal.ZERO)==-1){
			this.fdbv=BigDecimal.ZERO;
		}
		
	}
	
	public boolean compare(){
		if(this.fdbv.compareTo(this.kfdbv)==1){
			this.over=true;
		}
		return this.over;
	}
	
	
	
	
	
	 

	
}
