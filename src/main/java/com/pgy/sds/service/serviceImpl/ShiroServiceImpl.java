package com.pgy.sds.service.serviceImpl;

import com.google.common.collect.Sets;
import com.pgy.sds.common.constant.RedisKeyConstants;
import com.pgy.sds.common.constant.SysConstants;
import com.pgy.sds.dao.SysMenuMapper;
import com.pgy.sds.dao.SysUserMapper;
import com.pgy.sds.model.SysMenu;
import com.pgy.sds.model.SysUser;
import com.pgy.sds.model.SysUserToken;
import com.pgy.sds.service.ShiroService;
import com.pgy.sds.service.SysUserTokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 10:38
 * Description:
 */
@Service
public class ShiroServiceImpl implements ShiroService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysUserTokenService sysUserTokenService;

	/**
	 * 获取用户的所有权限
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> getUserPermissions(Integer userId) {
		List<String> permsList;

		//系统管理员，拥有最高权限
		if (SysConstants.SUPER_ADMIN.equals(userId)) {
			List<SysMenu> menuList = sysMenuMapper.selectList(null);
			permsList = new ArrayList<>(menuList.size());
			menuList.forEach(menu -> permsList.add(menu.getPerms()));
		} else {
			permsList = sysUserMapper.queryAllPerms(userId);
		}
		Set<String> permsSet = Sets.newHashSet();
		if (!permsList.isEmpty()) {
			permsSet = permsList.stream()
							// 过滤空置的字符串
							.filter(perms -> !StringUtils.isEmpty(perms))
							// 把小的list合并成大的list
							.flatMap(perms -> Arrays.stream(perms.split(",")))
							// 转换成set集合
							.collect(Collectors.toSet());
		}
		//返回用户权限列表
		return permsSet;
	}

	/**
	 * 查询token
	 *
	 * @param token
	 * @return
	 */
	@Override
	public SysUserToken queryByToken(String token) {
		return sysUserTokenService.queryByToken(RedisKeyConstants.MANAGE_SYS_USER_TOKEN + token);
	}

	/**
	 * 查询用户信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public SysUser queryUser(Integer userId) {
		return sysUserMapper.selectById(userId);
	}

	/**
	 * 续期
	 *
	 * @param userId
	 * @param accessToken
	 */
	@Override
	public void refreshToken(Integer userId, String accessToken) {
		sysUserTokenService.refreshToken(userId, accessToken);
	}
}
