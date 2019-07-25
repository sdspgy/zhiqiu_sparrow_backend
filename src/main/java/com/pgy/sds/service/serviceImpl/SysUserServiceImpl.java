package com.pgy.sds.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pgy.sds.dao.SysUserMapper;
import com.pgy.sds.model.SysUser;
import com.pgy.sds.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 17:34
 * Description:
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    /*查询所以用户信息（角色）*/
    @Override
    public List<SysUser> queryAllUser() {
        return sysUserMapper.queryAllUser();
    }
}
