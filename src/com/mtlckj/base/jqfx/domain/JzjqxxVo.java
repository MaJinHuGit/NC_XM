package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 
* @ClassName: Jzjqxx.java
* @Description:警钟警情信息表
*
* @version: v1.0.0
* @author: 唐青
* @date: 2018年10月18日 下午4:54:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年10月18日      唐青                                         v1.0.0             修改原因
 */
public class JzjqxxVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//接警单编号
	private String jjdwbh;
	//接警单位名称
	private String jjdwmc;
	//报警时间
	private Date bjsj;
	//警情类别
	private String jqlb;
	
	//警情发生开始时间
	private String jqkssj;
	//警情类别名称
	private String ct;
	
	//警情数量
	private String cunt;
	//派出所名称
	private String name;
	//本年、本季度、本月、本周
	private String cuntnow;
	//上季度、上月、上周
	private String cuntlast;
	//
	private String cuntyear;
	private String cuntlastt;
	//扒窃
	private String pq;
	//盗窃车内财物
	private String dqcncw;
	//盗窃摩托车
	private String dqmtc;
	//盗窃汽车
	private String dqqc;
	//非侵财
	private String fqc;
	//其他盗窃
	private String qtdq;
	//抢夺
	private String qd;
	//抢劫
	private String qj;
	//侵财
	private String qc;
	//入室盗窃
	private String rsdq;
	//诈骗
	private String zp;
	
	public String getJjdwbh() {
		return jjdwbh;
	}

	public void setJjdwbh(String jjdwbh) {
		this.jjdwbh = jjdwbh;
	}

	public String getJjdwmc() {
		return jjdwmc;
	}

	public void setJjdwmc(String jjdwmc) {
		this.jjdwmc = jjdwmc;
	}

	public Date getBjsj() {
		return bjsj;
	}

	public void setBjsj(Date bjsj) {
		this.bjsj = bjsj;
	}

	public String getJqlb() {
		return jqlb;
	}

	public void setJqlb(String jqlb) {
		this.jqlb = jqlb;
	}

	public String getJqkssj() {
		return jqkssj;
	}

	public void setJqkssj(String jqkssj) {
		this.jqkssj = jqkssj;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getCunt() {
		return cunt;
	}

	public void setCunt(String cunt) {
		this.cunt = cunt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuntnow() {
		return cuntnow;
	}

	public void setCuntnow(String cuntnow) {
		this.cuntnow = cuntnow;
	}

	public String getCuntlast() {
		return cuntlast;
	}

	public void setCuntlast(String cuntlast) {
		this.cuntlast = cuntlast;
	}

	public String getCuntyear() {
		return cuntyear;
	}

	public void setCuntyear(String cuntyear) {
		this.cuntyear = cuntyear;
	}

	public String getPq() {
		return pq;
	}

	public void setPq(String pq) {
		this.pq = pq;
	}

	public String getDqcncw() {
		return dqcncw;
	}

	public void setDqcncw(String dqcncw) {
		this.dqcncw = dqcncw;
	}

	public String getDqmtc() {
		return dqmtc;
	}

	public void setDqmtc(String dqmtc) {
		this.dqmtc = dqmtc;
	}

	public String getDqqc() {
		return dqqc;
	}

	public void setDqqc(String dqqc) {
		this.dqqc = dqqc;
	}

	public String getFqc() {
		return fqc;
	}

	public void setFqc(String fqc) {
		this.fqc = fqc;
	}

	public String getQtdq() {
		return qtdq;
	}

	public void setQtdq(String qtdq) {
		this.qtdq = qtdq;
	}

	public String getQd() {
		return qd;
	}

	public void setQd(String qd) {
		this.qd = qd;
	}

	public String getQj() {
		return qj;
	}

	public void setQj(String qj) {
		this.qj = qj;
	}

	public String getQc() {
		return qc;
	}

	public void setQc(String qc) {
		this.qc = qc;
	}

	public String getRsdq() {
		return rsdq;
	}

	public void setRsdq(String rsdq) {
		this.rsdq = rsdq;
	}

	public String getZp() {
		return zp;
	}

	public void setZp(String zp) {
		this.zp = zp;
	}

	public String getCuntlastt() {
		return cuntlastt;
	}

	public void setCuntlastt(String cuntlastt) {
		this.cuntlastt = cuntlastt;
	}

	

}
