package com.pgy.sds.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pgy.sds.dao.SysRoleMapper;
import com.pgy.sds.model.SysRole;
import com.pgy.sds.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   yuzhu·tao
 * Date:     2019/7/26 0:38
 * Description: 角色服务实现层
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
}
