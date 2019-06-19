package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;

public class DateCount implements Serializable {

	private static final long serialVersionUID = 1L;

	private String day;
	private String count;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "DateCount [day=" + day + ", count=" + count + "]";
	}
	
}
