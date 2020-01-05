package com.sys.controller;

import com.sys.entity.Emp;
import com.sys.entity.Menu;
import com.sys.entity.Role;
import com.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController {
    @Autowired
    private MenuService service;

    @RequestMapping({"menu_findByParentMenuIsNull"})
    @ResponseBody
    public List menuFindByParentMenuIsNull() {
        try {
            return this.service.menuFindByParentMenuIsNull();
        } catch (Exception localException) {
        }
        return null;
    }

    @RequestMapping("menu_findByParentMenuIsNullByEmp")
    public @ResponseBody
    List menu_findByParentMenuIsNullByEmp(HttpSession session) {
        Emp emp = (Emp) session.getAttribute("emp");
        return service.menu_findByParentMenuIsNullByEmp(emp);
    }

    @RequestMapping({"menu_add"})
    @ResponseBody
    public Map menuAdd(Menu menu) {
        try {
            this.service.menuAdd(menu);
            return ajaxReturn(true, "操作成功");
        } catch (Exception localException) {
        }
        return ajaxReturn(false, "操作失败");
    }

    @RequestMapping({"deleteMenuByid"})
    @ResponseBody
    public Map deleteMenuByid(Integer id) {
        try {
            this.service.deleteMenuByid(id);
            return ajaxReturn(true, "操作成功");
        } catch (Exception localException) {
        }
        return ajaxReturn(false, "操作失败");
    }

    @RequestMapping({"role_findAll"})
    @ResponseBody
    public List roleFindAll() {
        try {
            return this.service.roleFindAll();
        } catch (Exception localException) {
        }
        return null;
    }

    @RequestMapping({"role_add"})
    @ResponseBody
    public Map roleAdd(Role role, String menuIds) {
        try {
            this.service.roleAdd(role, menuIds);
            return ajaxReturn(true, "操作成功");
        } catch (Exception localException) {
        }
        return ajaxReturn(false, "操作失败");
    }

    @RequestMapping({"delete_role"})
    @ResponseBody
    public Map deleteRole(String ids) {
        try {
            this.service.deleteRole(ids);
            return ajaxReturn(true, "操作成功");
        } catch (Exception localException) {
        }
        return ajaxReturn(false, "操作失败");
    }

    @RequestMapping({"menu_findByRoleId"})
    @ResponseBody
    public List menufindByRoleId(Integer roleId) {
        try {
            return this.service.menufindByRoleId(roleId);
        } catch (Exception localException) {
        }
        return null;
    }

    @RequestMapping({"role_findRoleById"})
    @ResponseBody
    public Role roleFindRoleById(Integer roleId) {
        try {
            return this.service.roleFindRoleById(roleId);
        } catch (Exception localException) {
        }
        return null;
    }

    public Map ajaxReturn(boolean b, Object obj) {
        Map<String, Object> map = new HashMap();
        map.put("success", Boolean.valueOf(b));
        map.put("message", obj);
        return map;
    }
}
