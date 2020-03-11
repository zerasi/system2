package com.sys.service.impl;

import com.sys.dao.RoleDao;
import com.sys.dao.RolePermissionDao;
import com.sys.dao.RoleUserDao;
import com.sys.dto.RoleDto;
import com.sys.entity.SysPermission;
import com.sys.entity.SysRole;
import com.sys.entity.SysRoleUser;
import com.sys.service.RoleService;
import com.sys.utils.PageResult;
import com.sys.utils.ResponseCode;
import com.sys.utils.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

    @Autowired
    RolePermissionDao rolePermissionDao;

    @Autowired
    private RoleUserDao roleUserDao;

	@Override
	public Results<SysRole> getAllRoles() {
		return Results.success(50, roleDao.getAllRoles());
	}

	@Override
	public PageResult<SysRole> getAllRolesByPage(Integer offset, Integer limit) {
        return PageResult.addData(roleDao.countAllRoles(), roleDao.getAllRolesByPage(offset, limit));
	}

    @Override
    public Results<SysRole> save(RoleDto roleDto) {
        //1、先保存角色"
        roleDao.saveRole(roleDto);
        List<Long> permissionIds = roleDto.getPermissionIds();
        //移除0,permission id是从1开始
        permissionIds.remove(0L);
        //2、保存角色对应的所有权限
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.save(roleDto.getId(), permissionIds);
        }
        return Results.success();
    }

    @Override
    public SysRole getRoleById(Integer id) {
        return roleDao.getById(id);
    }

    @Override
    public Results update(RoleDto roleDto) {
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        //1、更新角色权限之前要删除该角色之前的所有权限
        rolePermissionDao.deleteRolePermission(roleDto.getId());

        //2、判断该角色是否有赋予权限值，有就添加"
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.save(roleDto.getId(), permissionIds);
        }

        //3、更新角色表
        int countData = roleDao.update(roleDto);

        if(countData > 0){
            return Results.success();
        }else{
            return Results.failure();
        }
    }

    @Override
    public Results delete(Integer id) {
        List<SysRoleUser> datas = roleUserDao.listAllSysRoleUserByRoleId(id);
        if(datas.size() <= 0){
            roleDao.delete(id);
            return Results.success();
        }
        return Results.failure(ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getCode(),ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getMessage());
    }

    @Override
    public Results<SysRole> getRoleByFuzzyRoleNamePage(String roleName, Integer startPosition, Integer limit) {
        return Results.success(roleDao.countRoleByFuzzyRoleName(roleName).intValue(), roleDao.getRoleByFuzzyRoleNamePage(roleName,startPosition, limit));
    }

    @Override
    public Results deleteByRoleIds(List<Long> roleIds) {
        /*roleIds.forEach(p -> {
            List<SysRoleUser> datas = roleUserDao.listAllSysRoleUserByRoleId(p.intValue());
            if(datas.size() > 0){
                SysRole role = this.roleDao.getById(p.intValue());
                return Results.failure(ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getCode(),role.getName()+"："+ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getMessage());
            }
        });*/
        for (Long id: roleIds){
            List<SysRoleUser> datas = roleUserDao.listAllSysRoleUserByRoleId(id.intValue());
            if(datas.size() > 0){
                SysRole role = this.roleDao.getById(id.intValue());
                return Results.failure(ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getCode(),role.getName()+"："+ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getMessage());
            }
        }
        roleIds.forEach(p ->{
            this.roleDao.delete(p.intValue());
            //this.roleUserDao.deleteRoleUserByRoleId(p.intValue());
            this.rolePermissionDao.deleteRolePermission(p.intValue());
        });
        return Results.success();
    }
}
