package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;

/**
 * <p>Title: HddJq</p> 
 * <p>Package: com.mtlckj.base.jqfx.domain</p>  
 * <p>Description: 黄赌毒警情信息表</p>   
 * <p>Copyright: Copyright (c) 2018</p> 
 * @author majinhu  
 * @date 2018年10月19日   
 * @version 1.0
 */
public class Hddjq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;//ID
	
	private String lb;//类别(01:黄 02:赌 03：毒)
	
	private String bjsj;//报警时间
	
	private String jbnr;//举报内容
	
	private String bldw;//办理单位
	
	private String bljg;//办理结果
	
	private String bz;//备注(房东、老板、嫌疑人员处理情况)
	
	private String sqmj;//社区民警

	/************************Getters and Setters**************************/
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getBjsj() {
		return bjsj;
	}

	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}

	public String getJbnr() {
		return jbnr;
	}

	public void setJbnr(String jbnr) {
		this.jbnr = jbnr;
	}

	public String getBldw() {
		return bldw;
	}

	public void setBldw(String bldw) {
		this.bldw = bldw;
	}

	public String getBljg() {
		return bljg;
	}

	public void setBljg(String bljg) {
		this.bljg = bljg;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSqmj() {
		return sqmj;
	}

	public void setSqmj(String sqmj) {
		this.sqmj = sqmj;
	}

	/*********************************************************************/
	
}
