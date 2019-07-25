package com.pgy.sds.controller;

import com.pgy.sds.model.Log;
import com.pgy.sds.model.Result;
import com.pgy.sds.model.SysRole;
import com.pgy.sds.model.SysUser;
import com.pgy.sds.service.SysRoleService;
import com.pgy.sds.service.SysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 17:09
 * Description:
 */
@RestController
@RequestMapping("/admin/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

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
        return Result.ok().put("user", user);
    }

    @PostMapping("/allUser")
    @RequiresPermissions(value = {"sys:user:queryAllUser", "sys:user:info"}, logical = Logical.AND)
    @Log(value = "所有用户信息")
    public Result queryAllUser(@RequestParam Map<String, Object> params) {
        List<SysUser> sysUsers = sysUserService.queryAllUser();
        return Result.ok().put("sysUsers", sysUsers);
    }

}
