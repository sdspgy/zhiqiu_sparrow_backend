package com.pgy.sds.controller.batchFactory;

import com.pgy.sds.dao.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-26 11:27
 * Description:
 */
@Service
public class UserDel implements BatchOperation {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public void operation(List<Integer> list) {
		list.forEach(user_id -> {
			sysUserMapper.deleteById(user_id);
		});
	}
}
