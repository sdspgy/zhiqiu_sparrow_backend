package com.pgy.sds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.sds.model.SysUser;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 17:15
 * Description:
 */
public interface SysUserService extends IService<SysUser> {

	List<SysUser> queryAllUser();
}
