package com.sys.dao;

import com.sys.entity.Menu;
import com.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public abstract interface MenuDao {
    public abstract List<Menu> menuFindByParentMenuIsNull();

    public abstract List<Menu> menuFindIsMyId(Integer paramInteger);

    public abstract void menuAdd(Menu paramMenu);

    public abstract List<Role> roleFindAll();

    public abstract int roleAdd(Role paramRole);

    public abstract void saveRoleMenu(int paramInt1, int paramInt2);

    public abstract List menufindByRoleId(Integer paramInteger);

    public abstract Role roleFindRoleById(Integer paramInteger);

    public abstract void deleteMenuByid(Integer paramInteger);

    public abstract void updateRole(Role paramRole);

    public abstract void deleteRoleMenuByRoleid(Integer paramInteger);

    public abstract void deleteRole(int paramInt);

    public abstract List<Menu> menuFindByParentMenuIsNullByEmp(Integer id, Integer pid);

    public abstract List<Menu> menuFindIsMyIdByEmp(Integer id, Integer id2);

    public abstract List<Menu> menuFindByParentMenuIsNullByEmpMid(Integer id, Integer id2);

}
