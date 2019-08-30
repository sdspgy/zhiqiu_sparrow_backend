package com.pgy.sds.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pgy.sds.dao.SysMenuMapper;
import com.pgy.sds.model.SysMenu;
import com.pgy.sds.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:25
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	/*查询完整权限树*/
	@Override
	public List<SysMenu> querySysMenuTree(Integer parentId) {
		return sysMenuMapper.querySysMenuTree(parentId);
	}

	@Override
	public void deletetMenuByMenuId(String menuId) {
		sysMenuMapper.deleteById(new QueryWrapper<SysMenu>().eq("menu_id", menuId));
	}

	@Override
	public void insertMenu(SysMenu sysMenu) {
		sysMenuMapper.insert(sysMenu);
	}
}
