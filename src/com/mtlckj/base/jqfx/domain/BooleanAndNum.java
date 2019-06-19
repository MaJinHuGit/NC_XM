package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;

public class BooleanAndNum implements Serializable {

	private static final long serialVersionUID = 1L;

	private String isParent;
	private String num;
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public BooleanAndNum(String isParent, String num) {
		super();
		this.isParent = isParent;
		this.num = num;
	}
	public BooleanAndNum() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
