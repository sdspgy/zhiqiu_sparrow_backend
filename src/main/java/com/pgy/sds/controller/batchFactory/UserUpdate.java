package com.pgy.sds.controller.batchFactory;

import com.pgy.sds.dao.SysUserMapper;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:25
 */
@Service
public class UserUpdate implements BatchOperation {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public void operation(List<Integer> list) {
		sysUserMapper.resetPassword(list, new Sha256Hash("123456", "123456").toHex(), "123456");
	}

}
