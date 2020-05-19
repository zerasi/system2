package com.sys.controller;

import com.sys.dto.UserDto;
import com.sys.entity.LoginUser;
import com.sys.entity.SysUser;
import com.sys.service.UserService;
import com.sys.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
//import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{username}")
    @ResponseBody
	public SysUser user(@PathVariable String username) {
	    log.info("UserController.user(): param ( username = " + username +" )");
		return userService.getUser(username);
	}

	@PostMapping("/getCurrentUser")
	@ApiOperation(value = "获取当前登录用户信息", notes = "获取当前用户登录信息")//描述
	@ResponseBody
	public LoginUser getUser() {
		LoginUser loginUser = (LoginUser) SecurityUtils.getCurrentUserAuthentication().getPrincipal();
		return loginUser;
	}

	@PostMapping("/list")
	@ApiOperation(value = "分页获取用户信息", notes = "分页获取用户信息")//描述
	@ApiImplicitParam(name = "request", value = "分页查询实体类", required=false)
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:query')")
	public PageResult getUsers(PageTableRequest request) {
		request.countOffset();
		return userService.getAllUsersByPage(request.getPage()-1,request.getRows());
	}

	@GetMapping(value = "/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @ApiOperation(value = "用户新增页面", notes = "跳转到新增用户信息页面")//描述
	public String addUser(Model model) {
		model.addAttribute("sysUser",new SysUser());
		return "pages/system/user_add";
	}

	@PostMapping(value = "/add")
	@ResponseBody
    @PreAuthorize("hasAuthority('sys:user:add')")
    @ApiOperation(value = "保存用户信息", notes = "保存新增的用户信息")//描述
	public Results<SysUser> saveUser(UserDto userDto, Integer roleId) {
		SysUser sysUser = null;
		sysUser = userService.getUser(userDto.getUsername());
		if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
			return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
		}
		sysUser = userService.getUserByPhone(userDto.getTelephone());
		if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
			return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
		}
		sysUser = userService.getUserByEmail(userDto.getEmail());
		if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
			return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(),ResponseCode.EMAIL_REPEAT.getMessage());
		}

		userDto.setStatus(1);
		userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		//userDto.setPassword(MD5.crypt(userDto.getPassword()));
		return userService.save(userDto,roleId);
	}

	String pattern = "yyyy-MM-dd";

	//只需要加上下面这段即可，注意不能忘记注解
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器
	}

    @GetMapping(value = "/edit")
    @ApiOperation(value = "用户编辑页面", notes = "跳转到用户信息编辑页面")//描述
    @ApiImplicitParam(name = "user", value = "用户实体类", dataType = "SysUser")
    public String editUser(Model model, SysUser user) {
        model.addAttribute("sysUser",userService.getUserById(user.getId()));
        return "pages/system/user_edit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
	@PreAuthorize("hasAuthority('sys:user:edit')")
    @ApiOperation(value = "保存用户信息", notes = "保存编辑完的用户信息")//描述
    public Results<SysUser> updateUser( UserDto userDto,Integer roleId) {
        SysUser sysUser = null;
        sysUser = userService.getUser(userDto.getUsername());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
        }
        sysUser = userService.getUserByPhone(userDto.getTelephone());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }
        sysUser = userService.getUserByEmail(userDto.getEmail());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(),ResponseCode.EMAIL_REPEAT.getMessage());
        }
        return userService.updateUser(userDto,roleId);
    }

    @PostMapping(value = "/delete")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:del')")
	@ApiOperation(value = "批量删除用户信息", notes = "批量删除用户信息")//描述
	@ApiImplicitParam(name = "userDto", value = "用户实体类", required = true, dataType = "UserDto")
	public Results deleteUser(@RequestBody UserDto userDto) {
		return userService.deleteUserByUserIds(userDto.getUserIds());
	}

	@PostMapping(value = "/lock")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:user:del')")
	@ApiOperation(value = "锁定用户或解锁", notes = "锁定用户，修改状态为2，解锁修改状态为1")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userIds",value = "用户id数组", required = true),
			@ApiImplicitParam(name = "status",value = "状态，1：正常，2：锁定", required = true)
	})
	public Results<SysUser> lockUser(@RequestBody UserDto userDto){
		userService.lockUser(userDto.getUserIds(),userDto.getStatus());
		return Results.success();
	}

    @GetMapping("/findUserByFuzzyUserName")
	@ResponseBody
    @PreAuthorize("hasAuthority('sys:user:query')")
	@ApiOperation(value = "模糊查询用户信息", notes = "模糊搜索查询用户信息")//描述
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username",value = "模糊搜索的用户名", required = true),
	})
	public Results<SysUser> getUserByFuzzyUserName(PageTableRequest requests, String username) {
		requests.countOffset();
		return userService.getUserByFuzzyUserNamePage(username,requests.getOffset(),requests.getRows());
	}

    @PostMapping("/changePassword")
    @ApiOperation(value = "修改密码")
    @ResponseBody
    public Results<SysUser> changePassword(String username, String oldPassword, String newPassword) {
        return userService.changePassword(username, oldPassword, newPassword);
    }
}
