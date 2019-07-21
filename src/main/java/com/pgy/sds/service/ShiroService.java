package com.pgy.sds.service;

import com.pgy.sds.model.SysUser;
import com.pgy.sds.model.SysUserToken;

import java.util.Set;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 10:36
 * Description:
 */
public interface ShiroService {

	/**
	 * 获取用户的所有权限
	 *
	 * @param userId
	 * @return
	 */
	Set<String> getUserPermissions(Integer userId);

	/**
	 * 查询token
	 *
	 * @param token
	 * @return
	 */
	SysUserToken queryByToken(String token);

	/**
	 * 查询用户信息
	 *
	 * @param userId
	 * @return
	 */
	SysUser queryUser(Integer userId);

	/**
	 * 续期
	 *
	 * @param userId
	 * @param accessToken
	 */
	void refreshToken(Integer userId, String accessToken);
}
