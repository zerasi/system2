package com.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.sys.entity.SysPermission;
import com.sys.utils.Results;

import java.util.List;

public interface PermissionService {

    JSONArray listAllPermission();

    Results<SysPermission> listByRoleId(Integer intValue);

    Results<SysPermission> getMenuAll();

    Results<SysPermission> save(SysPermission sysPermission);

    SysPermission getSysPermissionById(Integer id);

    Results  updateSysPermission(SysPermission sysPermission);

    Results delete(Integer id);

    List<SysPermission> getMenu();

    Results<SysPermission> getMenu(Long userId);

    List<SysPermission> listAllPermissionByLoginUserId(Integer id);
}
