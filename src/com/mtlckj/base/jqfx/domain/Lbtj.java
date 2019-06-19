package com.mtlckj.base.jqfx.domain;
/**
 * 类别统计，接收来自数据库的数据
 * @author sunny
 *
 */
public class Lbtj {
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

	public Lbtj(Integer zzs, Integer lastFirstYearMonthzs, Integer lastSecondYearMonthzs,
			Integer lastThirdYearMonthzs) {
		super();
		this.zzs = zzs;
		this.lastFirstYearMonthzs = lastFirstYearMonthzs;
		this.lastSecondYearMonthzs = lastSecondYearMonthzs;
		this.lastThirdYearMonthzs = lastThirdYearMonthzs;
	}

	public Lbtj() {
		super();
		// TODO Auto-generated constructor stub
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
	
}
