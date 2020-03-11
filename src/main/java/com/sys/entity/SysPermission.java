package com.sys.entity;

import lombok.Data;

import java.util.List;

@Data
public class SysPermission extends BaseEntity<Integer> {

	private static final long serialVersionUID = -6525908145032868837L;
	private Integer parentId;
	private String name;
	private String href;
	private Integer type;
	private String permission;
	private Integer sort;

	private String text;

	public String getText() {return name; }

	private List<SysPermission> child;

}
