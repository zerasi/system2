package com.sys.dao;

import com.sys.entity.SysPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionDao {

    @Select("select * from sys_permission t")
    List<SysPermission> findAll();

    @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
    List<SysPermission> listByRoleId(Integer roleId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_permission(parentId, name, href, type, permission, sort) values(#{parentId}, #{name}, #{href}, #{type}, #{permission}, #{sort})")
    int save(SysPermission e);

    @Select("select * from sys_permission t where t.id = #{id}")
    SysPermission getSysPermissionById(Integer id);

    int update(SysPermission e);

    @Delete("delete from sys_permission where id = #{id}")
    int deleteById(Integer id);

    @Delete("delete from sys_permission where parentId = #{parentId}")
    int deleteByParentId(Integer parentId);

    @Select("SELECT DISTINCT sp.*  " +
            "FROM sys_role_user sru " +
            "INNER JOIN sys_role_permission srp ON srp.roleId = sru.roleId " +
            "LEFT JOIN sys_permission sp ON srp.permissionId = sp.id " +
            "WHERE " +
            "sru.userId = #{userId}")
    List<SysPermission> listByUserId(@Param("userId") Long userId);

    @Select("SELECT DISTINCT t5.id,t5.parentId,t5.name,t5.href FROM sys_user t1 INNER JOIN sys_role_user t2 ON t1.id = t2.userId " +
            "INNER JOIN sys_role t3 ON t2.roleId = t3.id INNER JOIN sys_role_permission t4 ON t4.roleId = t3.id " +
            "INNER JOIN sys_permission t5 ON t4.permissionId = t5.id WHERE t1.id = #{id} " +
            "AND t5.type = 1 ORDER BY t5.sort")
    List<SysPermission> listAllPermissionByLoginUserId(Integer id);

    int deleteByIds(@Param("ids") List<Integer> ids);
}
