package com.mtlckj.base.common.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 定制分析，需要保存分析查询条件所用的实体
 * @author 杨智强
 *
 */
public class DzfxDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dzfxId;
	private String dzfxText;
	
	private Date createTime;
	private String dzfxName;
	
	/**
	 *定制分析类型,table代表表格，line表示曲线图，pie代表病状图，bar代表柱状图
	 */
	private String dzfxType;

	public Long getDzfxId() {
		return dzfxId;
	}

	public void setDzfxId(Long dzfxId) {
		this.dzfxId = dzfxId;
	}

	public String getDzfxText() {
		return dzfxText;
	}

	public void setDzfxText(String dzfxText) {
		this.dzfxText = dzfxText;
	}

	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDzfxName() {
		return dzfxName;
	}

	public void setDzfxName(String dzfxName) {
		this.dzfxName = dzfxName;
	}

	public String getDzfxType() {
		return dzfxType;
	}

	public void setDzfxType(String dzfxType) {
		this.dzfxType = dzfxType;
	}
	
	
}
