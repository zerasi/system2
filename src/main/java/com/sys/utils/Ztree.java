package com.sys.utils;

public class Ztree {
	
	private Integer id;
	private Integer pId;
	private String name;
	private String page;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Ztree(Integer id, Integer pId, String name, String page) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.page = page;
	}
	
}
