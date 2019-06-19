package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 全区各所辖区警情详情表
 * @author 王军
 *
 */
public class Details implements Serializable {

	private static final long serialVersionUID = 1L;
	//案件主键 XXZJBH
	private String id;
	
	//警情编号 JQBH
	private String jqbh;
	
	//接警单编号 JJDBH
	private String jjdbh;
	
	//警方来源代码 JQLYDM
	private String jqlydm;
	
	//接警方式代码/BD_DB_BJFSDM BJFSDM
	private String bjfsdm;
	
	//报警时间_日期时间 BJSJ_RQSJ
	private Date rqsj;
	
	//事件地址_地址名称 SFDZ_DZMC
	private String dzmc;
	
	//事件标准地点_地址名称 SFBZDZ_DZMC
	private String bzdz;
	
	//简要案情 JYAQ
	private String jyaq;
	
	//处警人_居民身份号码 CLJQR_JMSFHM
	private String sfzh;
	
	//接警人_姓名 JJR_XM
	private String xm;
	
	//接警单位_公安机关名称 JJDW_GAJGMC
	private String gajgmc;
	
	//接警单位_公安机关机构代码 JJDW_GAJGJGDM
	private String gajgdm;
	
	//报案人_姓名 BAR_XM
	private String bxm;
	
	//报案人_身份证件号码 BAR_CYZJ_ZJHM
	private String bsfzh;
	
	//报案人_联系电话 BAR_LXDH
	private String lxdh;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJqbh() {
		return jqbh;
	}

	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}

	public String getJjdbh() {
		return jjdbh;
	}

	public void setJjdbh(String jjdbh) {
		this.jjdbh = jjdbh;
	}

	public String getJqlydm() {
		return jqlydm;
	}

	public void setJqlydm(String jqlydm) {
		this.jqlydm = jqlydm;
	}

	public String getBjfsdm() {
		return bjfsdm;
	}

	public void setBjfsdm(String bjfsdm) {
		this.bjfsdm = bjfsdm;
	}

	public Date getRqsj() {
		return rqsj;
	}

	public void setRqsj(Date rqsj) {
		this.rqsj = rqsj;
	}

	public String getDzmc() {
		return dzmc;
	}

	public void setDzmc(String dzmc) {
		this.dzmc = dzmc;
	}

	public String getBzdz() {
		return bzdz;
	}

	public void setBzdz(String bzdz) {
		this.bzdz = bzdz;
	}

	public String getJyaq() {
		return jyaq;
	}

	public void setJyaq(String jyaq) {
		this.jyaq = jyaq;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getGajgmc() {
		return gajgmc;
	}

	public void setGajgmc(String gajgmc) {
		this.gajgmc = gajgmc;
	}

	public String getGajgdm() {
		return gajgdm;
	}

	public void setGajgdm(String gajgdm) {
		this.gajgdm = gajgdm;
	}

	public String getBxm() {
		return bxm;
	}

	public void setBxm(String bxm) {
		this.bxm = bxm;
	}

	public String getBsfzh() {
		return bsfzh;
	}

	public void setBsfzh(String bsfzh) {
		this.bsfzh = bsfzh;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	@Override
	public String toString() {
		return "Details [id=" + id + ", jqbh=" + jqbh + ", jjdbh=" + jjdbh + ", jqlydm=" + jqlydm + ", bjfsdm=" + bjfsdm
				+ ", rqsj=" + rqsj + ", dzmc=" + dzmc + ", bzdz=" + bzdz + ", jyaq=" + jyaq + ", sfzh=" + sfzh + ", xm="
				+ xm + ", gajgmc=" + gajgmc + ", gajgdm=" + gajgdm + ", bxm=" + bxm + ", bsfzh=" + bsfzh + ", lxdh="
				+ lxdh + "]";
	}

	
	
}
