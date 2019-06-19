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
public class Ztyp implements Serializable {
	private static final long serialVersionUID = 1L;
	

	//报警时间
	private Date bjsj;
	//警情类别
	private String jqlb;

	//警情类别名称
	private String ct;
	
	//警情数量
	private String cunt;
	//派出所名称
	private String name;
	//事发地址名称
	private String sfdzmc;
	//事发标准地址名称
	private String sfbzdzmc;
	//报案人姓名
	private String barxm;
	
	//报案人联系电话
	private String barlxdh;
	//报案人现住地址
	private String barxzdz;
	//接警人
	private String jjrxm;
	//接警单位 CJDW_GAJGJGDM
	private String jjdwjgdm;
	//警情状态代码
	private String jqztdm;
	//录入人姓名
	private String lrrxm;
	//录入人部门机构代码
	private String lrrbmjgdm;
	//警情性质
	private String jqxzdm;
	//简要案情
	private String jyaq;
	//经度
	private String jd;
	//纬度
	private String wd;
	
	private String dates;
	
	private String cuntnow;
	
	private String cuntlast;
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
	public String getSfdzmc() {
		return sfdzmc;
	}
	public void setSfdzmc(String sfdzmc) {
		this.sfdzmc = sfdzmc;
	}
	public String getSfbzdzmc() {
		return sfbzdzmc;
	}
	public void setSfbzdzmc(String sfbzdzmc) {
		this.sfbzdzmc = sfbzdzmc;
	}
	public String getBarxm() {
		return barxm;
	}
	public void setBarxm(String barxm) {
		this.barxm = barxm;
	}
	public String getBarlxdh() {
		return barlxdh;
	}
	public void setBarlxdh(String barlxdh) {
		this.barlxdh = barlxdh;
	}
	public String getBarxzdz() {
		return barxzdz;
	}
	public void setBarxzdz(String barxzdz) {
		this.barxzdz = barxzdz;
	}
	public String getJjrxm() {
		return jjrxm;
	}
	public void setJjrxm(String jjrxm) {
		this.jjrxm = jjrxm;
	}
	public String getJjdwjgdm() {
		return jjdwjgdm;
	}
	public void setJjdwjgdm(String jjdwjgdm) {
		this.jjdwjgdm = jjdwjgdm;
	}
	public String getJqztdm() {
		return jqztdm;
	}
	public void setJqztdm(String jqztdm) {
		this.jqztdm = jqztdm;
	}
	public String getLrrxm() {
		return lrrxm;
	}
	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}
	public String getLrrbmjgdm() {
		return lrrbmjgdm;
	}
	public void setLrrbmjgdm(String lrrbmjgdm) {
		this.lrrbmjgdm = lrrbmjgdm;
	}
	public String getJqxzdm() {
		return jqxzdm;
	}
	public void setJqxzdm(String jqxzdm) {
		this.jqxzdm = jqxzdm;
	}
	public String getJyaq() {
		return jyaq;
	}
	public void setJyaq(String jyaq) {
		this.jyaq = jyaq;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
	public String getWd() {
		return wd;
	}
	public void setWd(String wd) {
		this.wd = wd;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
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
	
	

	

}
