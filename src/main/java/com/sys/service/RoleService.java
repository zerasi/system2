package com.sys.service;

import com.sys.dto.RoleDto;
import com.sys.entity.SysRole;
import com.sys.utils.PageResult;
import com.sys.utils.Results;

import java.util.List;

public interface RoleService {

	Results<SysRole> getAllRoles();

	PageResult<SysRole> getAllRolesByPage(Integer offset, Integer limit);

	Results<SysRole> save(RoleDto roleDto);

	SysRole getRoleById(Integer id);

	Results update(RoleDto roleDto);

	Results delete(Integer id);

	Results<SysRole> getRoleByFuzzyRoleNamePage(String roleName, Integer offset, Integer limit);

    Results<SysRole> deleteByRoleIds(List<Long> roleIds);
}
