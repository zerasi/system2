package com.sys.controller;

import com.alibaba.fastjson.JSONArray;
import com.sys.dto.RoleDto;
import com.sys.entity.LoginUser;
import com.sys.entity.SysPermission;
import com.sys.service.PermissionService;
import com.sys.utils.Results;
import com.sys.utils.SecurityUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("permission")
@Slf4j
public class PermissionController {
    @Autowired
    private PermissionService permissionService;


    @PostMapping(value = "/listAllPermission")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:query')")
    @ApiOperation(value = "获取所有权限值", notes = "获取所有菜单的权限值")//描述
    public JSONArray listAllPermission() {
        return permissionService.listAllPermission();
    }

    @RequestMapping(value = "/listAllPermissionByLoginUserId", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取登录用户权限菜单", notes = "获取登录用户权限菜单")//描述
    public Results listAllPermissionByLoginUserId() {
        LoginUser loginUser = (LoginUser)SecurityUtils.getCurrentUserAuthentication().getPrincipal();
        return permissionService.listAllPermissionByLoginUserId(loginUser.getId().intValue());
    }

    @RequestMapping(value = "/listAllPermissionByRoleId", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取角色权限", notes = "根据角色Id去查询拥有的权限")//描述
    public Results<SysPermission> listAllPermissionByRoleId(RoleDto roleDto) {
        log.info(getClass().getName() + " : param =  " + roleDto);
        return permissionService.listByRoleId(roleDto.getId().intValue());
    }

    @GetMapping("/menuAll")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:query')")
    @ApiOperation(value = "获取所有权限值", notes = "获取所有菜单的权限值")//描述
    public Results getMenuAll(){
        return permissionService.getMenuAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:add')")
    @ApiOperation(value = "添加菜单", notes = "保存用户新增的菜单信息")//描述
    @ApiImplicitParam(name = "sysPermission", value = "菜单权限实体sysPermission", required = true, dataType = "SysPermission")
    public Results<SysPermission> savePermission(@RequestBody SysPermission permission) {
        //后端如果用springMVC的@RequestBody注解的话，则只能Json对象的字符串，不能接收Json对象
        return permissionService.save(permission);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
    public String editPermission(Model model, SysPermission permission) {
        model.addAttribute("sysPermission",permissionService.getSysPermissionById(permission.getId()));
        return "permission/permission-add";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @ApiOperation(value = "更新菜单信息", notes = "保存用户编辑完的菜单信息")//描述
    @ApiImplicitParam(name = "sysPermission", value = "菜单权限实体sysPermission", required = true, dataType = "SysPermission")
    public Results updatePermission(@RequestBody  SysPermission permission) {
        return permissionService.updateSysPermission(permission);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:menu:del')")
    @ApiOperation(value = "删除菜单", notes = "根据菜单Id去删除菜单")//描述
    public Results deletePermission(SysPermission sysPermission) {
        return permissionService.delete(sysPermission.getId());
    }


    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取菜单", notes = "获取用户所属角色下能显示的菜单")//描述
    @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "Long")
    public Results<SysPermission> getMenu(Long userId) {
        return permissionService.getMenu(userId);
    }

}
