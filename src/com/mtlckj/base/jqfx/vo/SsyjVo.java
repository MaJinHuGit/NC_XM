package com.mtlckj.base.jqfx.vo;

import java.io.Serializable;

/**
 * <p>Title: HddJq</p> 
 * <p>Package: com.mtlckj.base.jqfx.domain</p>  
 * <p>Description: 四色预警Vo</p>   
 * <p>Copyright: Copyright (c) 2018</p> 
 * @author liangxiao
 * @date 2018年10月24日   
 * @version 1.0
 */
public class SsyjVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String jqsl; //警情数量
	private String szjz; //四周均值
	private String zs; //增速
	private String orgname; //组织机构名称
	
	/************************Getters and Setters**************************/
	
	public String getJqsl() {
		return jqsl;
	}
	public void setJqsl(String jqsl) {
		this.jqsl = jqsl;
	}
	public String getSzjz() {
		return szjz;
	}
	public void setSzjz(String szjz) {
		this.szjz = szjz;
	}
	public String getZs() {
		return zs;
	}
	public void setZs(String zs) {
		this.zs = zs;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
	/*********************************************************************/
	
}
