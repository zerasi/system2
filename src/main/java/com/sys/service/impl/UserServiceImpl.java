package com.sys.service.impl;

import com.sys.dao.RoleUserDao;
import com.sys.dao.UserDao;
import com.sys.dto.UserDto;
import com.sys.entity.SysRoleUser;
import com.sys.entity.SysUser;
import com.sys.service.UserService;
import com.sys.utils.PageResult;
import com.sys.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleUserDao roleUserDao;

	@Override
	public SysUser getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public PageResult<SysUser> getAllUsersByPage(Integer startPosition, Integer limit) {
		return PageResult.addData(userDao.countAllUsers(),userDao.getAllUsersByPage(startPosition,limit));
	}

	@Override
	public SysUser getUserByPhone(String phone) {
		return userDao.getUserByPhone(phone);
	}

	@Override
	public SysUser getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public Results save(SysUser user,Integer roleId) {

		if(roleId != null){
			userDao.save(user);
			SysRoleUser sysRoleUser = new SysRoleUser();
			sysRoleUser.setRoleId(roleId);
			sysRoleUser.setUserId(user.getId().intValue());
			roleUserDao.save(sysRoleUser);
			return Results.success();
		}
		return Results.failure();
	}

    @Override
    public SysUser getUserById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public Results updateUser(UserDto userDto, Integer roleId) {
        if(roleId != null){
            userDao.updateUser(userDto);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(userDto.getId().intValue());
            sysRoleUser.setRoleId(roleId);
            if(roleUserDao.getSysRoleUserByUserId(userDto.getId().intValue())!= null){
                roleUserDao.updateSysRoleUser(sysRoleUser);
            }else{
                roleUserDao.save(sysRoleUser);
            }
            return Results.success();
        }else{
            return Results.failure();
        }
    }

    public int deleteUser(Long id) {
        roleUserDao.deleteRoleUserByUserId(id.intValue());
        return userDao.deleteUser(id);
    }

	@Override
	public Results<SysUser> getUserByFuzzyUserNamePage(String username, Integer startPosition, Integer limit) {
		return Results.success(userDao.getUserByFuzzyUserName(username).intValue(),userDao.getUserByFuzzyUserNamePage(username,startPosition,limit));
	}

	@Override
	public Results<SysUser> changePassword(String username, String oldPassword, String newPassword) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		SysUser u = userDao.getUser(username);
		if (u == null) {
			return Results.failure(1,"用户不存在");
		}
		if (!bCryptPasswordEncoder.matches(oldPassword,u.getPassword())) {
			return Results.failure(1,"旧密码错误");
		}
		userDao.changePassword(u.getId(), bCryptPasswordEncoder.encode(newPassword));
		return Results.success();
	}

	@Override
	public void lockUser(List<Long> userIds,Integer status) {
		this.userDao.updateUserStatus(userIds,status);
	}

	@Override
	public Results deleteUserByUserIds(List<Long> userIds) {
		for (Long userId : userIds){
			this.userDao.deleteUser(userId);
			this.roleUserDao.deleteRoleUserByUserId(userId.intValue());
		}
		return Results.success();
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String pwd = bCryptPasswordEncoder.encode("admin");
		System.out.println(pwd);
		pwd = "$2a$10$DFIwAy//Ol3X6Q1e5CEue.FfUnJ5Fj709z9oY1pwCWzpca.SpYs72";
		boolean b = bCryptPasswordEncoder.matches("admin",pwd);
		System.out.println(b);
	}
}
