package com.pgy.sds.service;

import com.pgy.sds.model.Result;
import com.pgy.sds.model.SysUserToken;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 10:06
 * Description:
 */
public interface SysUserTokenService {

	/**
	 * 生成Token
	 *
	 * @param userId
	 * @return
	 */
	Result createToken(Integer userId);

	/**
	 * 查询token
	 *
	 * @param token
	 * @return
	 */
	SysUserToken queryByToken(String token);

	/**
	 * 退出登录
	 *
	 * @param userId
	 */
	void logout(Integer userId);

	/**
	 * 续期
	 *
	 * @param userId
	 * @param token
	 */
	void refreshToken(Integer userId, String token);
}