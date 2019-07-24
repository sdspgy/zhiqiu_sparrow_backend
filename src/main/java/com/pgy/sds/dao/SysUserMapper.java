package com.pgy.sds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pgy.sds.model.SysUser;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 09:40
 * Description:
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 查询用户所有权限
	 *
	 * @param userId
	 * @return
	 */
	List<String> queryAllPerms(Integer userId);

	List<SysUser> queryAllUser();
}
