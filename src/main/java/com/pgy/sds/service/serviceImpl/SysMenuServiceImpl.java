package com.pgy.sds.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pgy.sds.dao.SysMenuMapper;
import com.pgy.sds.model.SysMenu;
import com.pgy.sds.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   yuzhu·tao
 * Date:     2019/7/24 23:20
 * Description:
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> querySysMenuTree(Integer parentId) {
        return sysMenuMapper.querySysMenuTree(parentId);
    }
}
