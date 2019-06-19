package com.mtlckj.base.jqfx.domain;

import java.io.Serializable;

public class Ztree implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pid;
	private String name;
	private boolean open;
	private boolean isParent;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public Ztree(String id, String pid, String name, boolean open, boolean isParent) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.open = open;
		this.isParent = isParent;
	}
	public Ztree() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
