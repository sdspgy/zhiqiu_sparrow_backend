package com.pgy.sds.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pgy.sds.dao.SysRoleMapper;
import com.pgy.sds.model.SysRole;
import com.pgy.sds.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:26
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	/*查询用户所以角色*/
	@Override
	public List<SysRole> queryUserRoles(Integer userId) {
		return sysRoleMapper.queryUserRoles(userId);
	}

	@Override
	public List<SysRole> queryAllRoles() {
		return sysRoleMapper.selectList(new QueryWrapper<SysRole>().lambda().orderByAsc(SysRole::getRoleId));
	}

	@Override
	public void deleteRoleByRoleId(String roleId) {
		sysRoleMapper.deleteById(new QueryWrapper<SysRole>().eq("role_id", roleId));
	}

	@Override
	public void insertRole(SysRole sysRole) {
		sysRoleMapper.insert(sysRole);
	}
}
