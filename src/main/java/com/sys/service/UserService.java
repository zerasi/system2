package com.sys.service;
import com.sys.dto.UserDto;
import com.sys.entity.SysUser;
import com.sys.utils.PageResult;
import com.sys.utils.Results;

import java.util.List;

public interface UserService {

	SysUser getUser(String username);

	PageResult<SysUser> getAllUsersByPage(Integer startPosition, Integer limit);

	SysUser getUserByPhone(String phone);

	SysUser getUserByEmail(String email);

    Results save(SysUser user, Integer roleId);

    SysUser getUserById(Long id);

    Results updateUser(UserDto userDto, Integer roleId);

    int deleteUser(Long id);

    Results<SysUser> getUserByFuzzyUserNamePage(String username, Integer startPosition, Integer limit);

    Results changePassword(String username, String oldPassword, String newPassword);

    void lockUser(List<Long> userIds,Integer status);

    Results deleteUserByUserIds(List<Long> userIds);
}
