package com.sys.service.impl;


import java.util.List;

import com.sys.dao.MenuDao;
import com.sys.entity.Emp;
import com.sys.entity.Menu;
import com.sys.entity.Role;
import com.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class MenuServiceImpl
        implements MenuService {
    @Autowired
    private MenuDao dao;

    public void menuChild(List<Menu> list) {
        for (Menu menu : list) {
            List<Menu> list2 = this.dao.menuFindIsMyId(menu.getId());
            menu.setChildrenMenus(list2);
            if (list2.size() > 0) {
                menuChild(list2);
            }
        }
    }

    public void menuChildByEmp(List<Menu> list, Emp emp) {
        for (Menu menu : list) {
            List<Menu> list2 = this.dao.menuFindByParentMenuIsNullByEmpMid(emp.getId(), menu.getId());
            menu.setChildrenMenus(list2);
            if (list2.size() > 0) {
                menuChildByEmp(list2, emp);
            }
        }
    }


    public List menu_findByParentMenuIsNullByEmp(Emp emp) {
        List<Menu> list = this.dao.menuFindByParentMenuIsNullByEmp(emp.getId(), 0);
        System.out.println(JSON.toJSONString(list));
	    /*for (Menu menu : list)
	    {
	      List<Menu> list2 = this.dao.menuFindIsMyIdByEmp(menu.getId(),emp.getId());
	      menu.setChildrenMenus(list2);
	    }*/
        this.menuChildByEmp(list, emp);
        System.out.println(JSON.toJSONString(list));
        return list;
    }

    public List menuFindByParentMenuIsNull() {
        List<Menu> list = this.dao.menuFindByParentMenuIsNull();
   /* for (Menu menu : list)
    {
      List<Menu> list2 = this.dao.menuFindIsMyId(menu.getId());
      menu.setChildrenMenus(list2);
    }*/
        this.menuChild(list);
        return list;
    }


    public void menuAdd(Menu menu) {
        this.dao.menuAdd(menu);
    }

    public List<Role> roleFindAll() {
        return this.dao.roleFindAll();
    }

    public void roleAdd(Role role, String ids) {
        if (role.getId() == null) {
            this.dao.roleAdd(role);
            String[] arr = ids.split(",");
            String[] arrayOfString1;
            int j = (arrayOfString1 = arr).length;
            for (int i = 0; i < j; i++) {
                String s = arrayOfString1[i];
                this.dao.saveRoleMenu(role.getId().intValue(), Integer.parseInt(s));
            }
        } else {
            this.dao.updateRole(role);
            this.dao.deleteRoleMenuByRoleid(role.getId());
            String[] arr = ids.split(",");
            String[] arrayOfString1;
            int j = (arrayOfString1 = arr).length;
            for (int i = 0; i < j; i++) {
                String s = arrayOfString1[i];
                this.dao.saveRoleMenu(role.getId().intValue(), Integer.parseInt(s));
            }
        }
    }

    public List menufindByRoleId(Integer roleId) {
        return this.dao.menufindByRoleId(roleId);
    }

    public Role roleFindRoleById(Integer roleId) {
        return this.dao.roleFindRoleById(roleId);
    }

    public void deleteMenuByid(Integer id) {
        this.dao.deleteMenuByid(id);
    }

    public void deleteRole(String ids) {
        String[] arr = ids.split(",");
        String[] arrayOfString1;
        int j = (arrayOfString1 = arr).length;
        for (int i = 0; i < j; i++) {
            String s = arrayOfString1[i];
            this.dao.deleteRole(Integer.parseInt(s));
            this.dao.deleteRoleMenuByRoleid(Integer.valueOf(Integer.parseInt(s)));
        }
    }


}
