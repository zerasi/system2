package com.sys.dto;



import com.sys.entity.SysRole;

import java.util.List;

public class RoleDto extends SysRole {

	private static final long serialVersionUID = -5784234789156935003L;

	private List<Long> permissionIds;

	public List<Long> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Long> permissionIds) {
		this.permissionIds = permissionIds;
	}

	private List<Long> roleIds;

	public List<Long> getRoleIds(){
		return this.roleIds;
	}

	public void setRoleIds(List<Long> roleIds){
		this.roleIds = roleIds;
	}
}
