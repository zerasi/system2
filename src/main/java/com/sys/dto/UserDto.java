package com.sys.dto;


import com.sys.entity.SysUser;

import java.util.List;

public class UserDto extends SysUser {
	private static final long serialVersionUID = -5784234789156935003L;

	private List<Long> userIds;

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
}
