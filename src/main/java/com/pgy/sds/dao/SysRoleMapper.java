package com.pgy.sds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pgy.sds.model.SysRole;

import java.util.List;

/**
 * Author:   yuzhu·tao
 * Date:     2019/7/26 0:39
 * Description: 角色接口层
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /*查询用户所以角色*/
    List<SysRole> queryUserRoles(Integer userId);
}
