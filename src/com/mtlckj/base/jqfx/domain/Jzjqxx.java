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
public class Jzjqxx implements Serializable {
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

	

}
