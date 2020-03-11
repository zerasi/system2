package com.sys.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.sys.dao.PermissionDao;
import com.sys.dao.RolePermissionDao;
import com.sys.entity.SysPermission;
import com.sys.service.PermissionService;
import com.sys.utils.Results;
import com.sys.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.verifier.statics.LONG_Upper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public JSONArray listAllPermission() {
        log.info(getClass().getName() + ".listAllPermission()");
        List datas = permissionDao.findAll();
        JSONArray array = new JSONArray();
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0, datas, array);
        return array;
    }

    @Override
    public Results<SysPermission> listByRoleId(Integer roleId) {
        return Results.success(0, permissionDao.listByRoleId(roleId));
    }

    @Override
    public Results<SysPermission> getMenuAll() {
        return Results.success(0, permissionDao.findAll());
    }

    @Override
    public Results save(SysPermission sysPermission) {
        return (permissionDao.save(sysPermission) > 0) ? Results.success() : Results.failure();
    }

    @Override
    public SysPermission getSysPermissionById(Integer id) {
        return permissionDao.getSysPermissionById(id);
    }

    @Override
    public Results updateSysPermission(SysPermission sysPermission) {
        return (permissionDao.update(sysPermission) > 0) ? Results.success() : Results.failure();
    }

    @Override
    public Results delete(Integer id) {
        //删除节点 及以下节点
        List<SysPermission> permissions = permissionDao.findAll();
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        deleteForeach(id,permissions,ids);

        this.permissionDao.deleteByIds(ids);

        //删除权限角色中间表sys_role_permission 权限id
        this.rolePermissionDao.deleteByPermissionIds(ids);
        return Results.success();
    }

    public void deleteForeach(Integer pId,List<SysPermission> permissions,List<Integer> ids){
        for(SysPermission permission : permissions){
            if(permission.getParentId()!=null && permission.getParentId().equals(pId)){
                //ParentId = pId添加到 ids集合
                ids.add(permission.getId());
                if(permissions.stream().filter(p ->
                        p.getParentId()!=null && p.getParentId().equals(permission.getId())).findAny()!=null){
                    //存在元素的ParentId = permission.getId()
                    deleteForeach(permission.getId(),permissions,ids);
                }
            }
        }
    }

    @Override
    public List<SysPermission> getMenu() {
        return permissionDao.findAll();
    }

    public Results getMenu(Long userId) {
        List<SysPermission> datas = permissionDao.listByUserId(userId);
        datas = datas.stream().filter(p -> p.getType().equals(1)).collect(Collectors.toList());
        JSONArray array = new JSONArray();
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }

    @Override
    public List<SysPermission> listAllPermissionByLoginUserId(Integer id) {
        return this.permissionDao.listAllPermissionByLoginUserId(id);
    }

}
