package com.pgy.sds.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pgy.sds.dao.SysRoleMapper;
import com.pgy.sds.dao.SysRoleMenuMapper;
import com.pgy.sds.model.*;
import com.pgy.sds.service.SysMenuService;
import com.pgy.sds.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:25
 */
@RestController
@RequestMapping("/admin/sys/role")
public class SysRoleController extends AbstractController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@PostMapping("/queryAllRoles")
	@RequiresPermissions("sys:role:info")
	@Log(value = "查询所有角色")
	public Result queryAllRoles() {
		List<SysRole> sysRoles = sysRoleService.queryAllRoles();
		return Result.ok().put("sysRoles", sysRoles);
	}

	@PostMapping("/insertRole")
	@RequiresPermissions("sys:role:info")
	@Log(value = "添加角色")
	public Result insertRole(@RequestBody SysRole sysRole) {
		sysRoleService.insertRole(sysRole);
		return Result.ok();
	}

	@PostMapping("/updateRole")
	@RequiresPermissions("sys:role:info")
	@Log(value = "修改角色")
	public Result updateRole(@RequestBody SysRole sysRole) {
		sysRoleService.updateById(sysRole);
		return Result.ok();
	}

	@PostMapping("/deleteRole/{roleId}")
	@RequiresPermissions("sys:role:info")
	@Log(value = "删除角色")
	public Result deleteRole(@PathVariable("roleId") String roleId) {
		sysRoleService.deleteRoleByRoleId(roleId);
		return Result.ok();
	}

	@GetMapping("/queryAllMenuIshave/{roleId}")
	@RequiresPermissions("sys:user:queryAllMenuIshave")
	@Log(value = "角色拥有的资源")
	public Result queryAllMenuIshave(@PathVariable Integer roleId) {
		List<SysRoleMenu> isHaveMenus = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
		List<Integer> isHaveMenuIds = isHaveMenus.stream().map(info -> info.getMenuId()).collect(Collectors.toList());
		List<SysMenu> sysMenus = sysMenuService.querySysMenuTree(0);
		return Result.ok().put("sysMenus", sysMenus).put("isHaveMenuIds", isHaveMenuIds);
	}

	@PostMapping("updateRoleMenus")
	@RequiresPermissions("sys:user:updateMenus")
	@Log(value = "修改角色资源")
	public Result updateRoleMenus(@RequestParam Map<String, String> param) {
		String[] menus = param.get("menus").split(",");
		String roleId = param.get("roleId");
		sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));

		for (int i = 0; i < menus.length; i++) {
			SysRoleMenu sysUserRole = new SysRoleMenu();
			sysUserRole.setMenuId(Integer.parseInt(menus[i]));
			sysUserRole.setRoleId(Integer.parseInt(roleId));
			sysRoleMenuMapper.insert(sysUserRole);
		}
		return Result.ok();
	}
}
