package com.pgy.sds.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pgy.sds.model.SysMenu;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 10:42
 * Description:
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> querySysMenuTree(Integer parentId);
}
