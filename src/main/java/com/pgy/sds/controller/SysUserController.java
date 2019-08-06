package com.pgy.sds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.pgy.sds.controller.batchFactory.UserBatchType;
import com.pgy.sds.dao.SysUserRoleMapper;
import com.pgy.sds.model.*;
import com.pgy.sds.service.SysRoleService;
import com.pgy.sds.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 17:09
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/admin/sys/user")
@EnableScheduling
public class SysUserController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	//	@Scheduled(fixedRate = 5000)
	@Scheduled(cron = "0 30 5 * * ?")
	public void timeTask() {
		log.info("--------" + "定时任务" + "--------");
	}

	/**
	 * 用户信息
	 */
	@GetMapping("/info")
	@RequiresPermissions("sys:user:info")
	@Log(value = "用户信息")
	public Result info(Integer userId) {
		if (StringUtils.isEmpty(userId)) {
			userId = getUserId();
		}
		SysUser user = sysUserService.getById(userId);
		/*获取用户所属的角色列表*/
		List<SysRole> roleList = sysRoleService.queryUserRoles(userId);
		user.setRoleList(roleList);
		return Result.ok().put("user", user);
	}

	@PostMapping("/allUser")
	@RequiresPermissions(value = { "sys:user:queryAllUser", "sys:user:info" }, logical = Logical.AND)
	@Log(value = "所有用户信息(角色）")
	public Result queryAllUser(@RequestParam Map<String, String> params) {
		List<SysUser> sysUsers = sysUserService.queryAllUser();
		return Result.ok().put("sysUsers", sysUsers);
	}

	@PostMapping("/batchOperation")
	@RequiresPermissions(value = { "sys:user:queryAllUser" })
	@Log(value = "用户批量操作")
	public Result userBatchOperation() {
		List<Integer> list = Lists.newArrayList();
		list.add(1);
		list.add(2);
		int[] type = new int[] { 0, 1 };
		UserBatchType.getUserBatchTypeAsCode(type[0]).batch(list);
		return Result.ok();
	}

	@PostMapping("/updateUser")
	@RequiresPermissions("sys:user:update")
	@Log(value = "用户修改")
	public Result updateUser(@RequestBody SysUser sysUser) {
		sysUserService.updateById(sysUser);
		return Result.ok();
	}

	@PostMapping("/insertUser")
	@RequiresPermissions("sys:user:insert")
	@Log(value = "用户添加")
	public Result insertUser(@RequestParam Map<String, Object> params, Integer[] roleIdList) {
		int userId = new Random().nextInt();
		SysUser sysUser = new SysUser();
		sysUser.setUserId(userId);
		sysUser.setUsername(String.valueOf(params.get("username")));
		sysUser.setStatus((String) params.get("status"));
		sysUserService.insertUser(sysUser);

		List<Object> roleIdList1 = Arrays.asList(params.get("roleIdList"));
		for (Object o : roleIdList) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleId(Integer.parseInt((String) o));
			sysUserRoleMapper.insert(sysUserRole);
		}
		return Result.ok();
	}

	@PostMapping("/deletetUser/{userId}")
	@RequiresPermissions("sys:user:deletet")
	@Log(value = "用户删除")
	public Result deletetUser(@PathVariable("userId") String userId) {
		sysUserService.deletetUserByUserId(userId);
		return Result.ok();
	}

	@GetMapping("/queryAllRoleIshave/{userId}")
	@RequiresPermissions("sys:user:queryallRoleIshave")
	@Log(value = "用户拥有的角色")
	public Result queryAllRoleIshave(@PathVariable Integer userId) {
		List<SysRole> isSysRoles = sysRoleService.queryUserRoles(userId);
		List<SysRole> sysRoles = sysRoleService.queryAllRoles();
		sysRoles.forEach(info -> {
			if (isSysRoles.contains(info)) {
				info.setIsHave(true);
			}
		});
		return Result.ok().put("sysRoles", sysRoles);
	}

	@PostMapping("updateUserRoles")
	@RequiresPermissions("sys:user:updateRoles")
	@Log(value = "修改用户角色")
	public Result updateUserRoles(@RequestParam Map<String, String> param) {
		String[] roles = param.get("roles").split(",");
		String userId = param.get("userId");
		sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id", userId));
		for (int i = 0; i < roles.length; i++) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setRoleId(Integer.parseInt(roles[i]));
			sysUserRole.setUserId(Integer.parseInt(userId));
			sysUserRoleMapper.insert(sysUserRole);
		}
		return Result.ok();
	}

}
