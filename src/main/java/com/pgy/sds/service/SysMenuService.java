package com.pgy.sds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.sds.model.SysMenu;

import java.util.List;

/**
 * Author:   yuzhu·tao
 * Date:     2019/7/24 23:19
 * Description:
 */
public interface SysMenuService extends IService<SysMenu> {

    /*查询完整权限树*/
    List<SysMenu> querySysMenuTree(Integer parentId);
}
