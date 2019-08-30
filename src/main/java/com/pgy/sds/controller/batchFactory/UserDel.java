package com.pgy.sds.controller.batchFactory;

import com.pgy.sds.dao.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:24
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
