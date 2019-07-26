package com.pgy.sds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.sds.model.SysRole;

import java.util.List;

/**
 * Author:   yuzhu·tao
 * Date:     2019/7/26 0:36
 * Description: 角色服务层
 */
public interface SysRoleService extends IService<SysRole> {

	/*查询用户所以角色*/
	List<SysRole> queryUserRoles(Integer userId);

	List<SysRole> queryAllRoles();

	void deleteRoleByRoleId(String roleId);

	void insertRole(SysRole sysRole);
}
