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
    List<SysMenu> querySysMenuTree(Integer parentId);
}
