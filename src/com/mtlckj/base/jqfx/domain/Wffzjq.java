package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;


/**
 * 违法犯罪警情表
 * @author liangxiao
 * @date 2018年10月18日 上午9:52:28
 */
public class Wffzjq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//接警单编号
	private String jjdbh;
	//接入时间
	private String jrsj;
	//报警时间
	private String bjsj;
	//报警类别
	private String bjlb;
	//报警类型
	private String bjlx;
	//报警细类
	private String bjxl;
	//报警电话
	private String bjdh;
	//案发地址
	private String afdz;
	//结案描述
	private String jams;
	//关键字
	private String gjz;
	//报警内容
	private String bjnr;
	//管辖单位
	private String gxdw;
	//接警录音号
	private String jjlyh;
	
	private String cunt;
	public String getJjdbh() {
		return jjdbh;
	}
	public void setJjdbh(String jjdbh) {
		this.jjdbh = jjdbh;
	}
	public String getJrsj() {
		return jrsj;
	}
	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
	}
	public String getBjsj() {
		return bjsj;
	}
	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}
	public String getBjlb() {
		return bjlb;
	}
	public void setBjlb(String bjlb) {
		this.bjlb = bjlb;
	}
	public String getBjxl() {
		return bjxl;
	}
	public void setBjxl(String bjxl) {
		this.bjxl = bjxl;
	}
	public String getBjdh() {
		return bjdh;
	}
	public void setBjdh(String bjdh) {
		this.bjdh = bjdh;
	}
	public String getAfdz() {
		return afdz;
	}
	public void setAfdz(String afdz) {
		this.afdz = afdz;
	}
	public String getJams() {
		return jams;
	}
	public void setJams(String jams) {
		this.jams = jams;
	}
	public String getGjz() {
		return gjz;
	}
	public void setGjz(String gjz) {
		this.gjz = gjz;
	}
	public String getBjnr() {
		return bjnr;
	}
	public void setBjnr(String bjnr) {
		this.bjnr = bjnr;
	}
	public String getGxdw() {
		return gxdw;
	}
	public void setGxdw(String gxdw) {
		this.gxdw = gxdw;
	}
	public String getJjlyh() {
		return jjlyh;
	}
	public void setJjlyh(String jjlyh) {
		this.jjlyh = jjlyh;
	}
	public String getBjlx() {
		return bjlx;
	}
	public void setBjlx(String bjlx) {
		this.bjlx = bjlx;
	}
	public String getCunt() {
		return cunt;
	}
	public void setCunt(String cunt) {
		this.cunt = cunt;
	}
	
}
