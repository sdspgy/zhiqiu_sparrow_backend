package com.pgy.sds.controller;

import com.pgy.sds.model.Log;
import com.pgy.sds.model.Result;
import com.pgy.sds.model.SysMenu;
import com.pgy.sds.service.SysMenuService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author:   yuzhu·tao
 * Date:     2019/7/24 23:25
 * Description:
 */
@RestController
@RequestMapping("/admin/sys/menu")
public class SysMenuController extends AbstractController {

	@Autowired
	private SysMenuService sysMenuService;

	@GetMapping("/menuTree")
	@RequiresPermissions(value = { "sys:menu:list", "sys:menu:info" }, logical = Logical.OR)
	@Log(value = "权限树")
	public Result queryMenuTree() {
		List<SysMenu> sysMenus = sysMenuService.querySysMenuTree(0);
		return Result.ok().put("sysMenus", sysMenus);
	}

	@PostMapping("/insertMenu")
	@RequiresPermissions("sys:menu:info")
	@Log(value = "资源添加")
	public Result insertMenu(@RequestBody SysMenu sysMenu) {
		sysMenuService.insertMenu(sysMenu);
		return Result.ok();
	}

	@PostMapping("/updateMenu")
	@RequiresPermissions("sys:menu:info")
	@Log(value = "资源修改")
	public Result updateMenu(@RequestBody SysMenu sysMenu) {
		sysMenuService.updateById(sysMenu);
		return Result.ok();
	}

	@PostMapping("/deletetMenu/{menuId}")
	@RequiresPermissions("sys:menu:deletet")
	@Log(value = "资源删除")
	public Result deletetUser(@PathVariable("menuId") String menuId) {
		sysMenuService.deletetMenuByMenuId(menuId);
		return Result.ok();
	}
}
