package com.mtlckj.base.jqfx.vo;

import java.io.Serializable;
/**
 * 诈骗热点类
 * @author sunny
 *
 */
public class SsyjHotMark implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String lng; //经度
	private String lat; //纬度
	private String id;
	private String title;
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SsyjHotMark(String lng, String lat, String id, String title) {
		super();
		this.lng = lng;
		this.lat = lat;
		this.id = id;
		this.title = title;
	}
	public SsyjHotMark() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
