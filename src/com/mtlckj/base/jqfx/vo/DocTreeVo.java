package com.mtlckj.base.jqfx.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 文件树对象
 * @author 杨智强
 *
 */
public class DocTreeVo implements Serializable{
	
	// long serialVersionUID :
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Boolean spread;
	private String href;
	private List<DocTreeVo> children;
	private String alias;
	private Long id;
	private String dataType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSpread() {
		return spread;
	}
	public void setSpread(Boolean spread) {
		this.spread = spread;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	public List<DocTreeVo> getChildren() {
		return children;
	}
	public void setChildren(List<DocTreeVo> children) {
		this.children = children;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	
	
	

}
