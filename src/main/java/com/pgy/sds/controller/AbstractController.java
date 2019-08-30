package com.pgy.sds.controller;

import com.pgy.sds.model.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:22
 */
public class AbstractController {

	protected SysUser getUser() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}

	protected Integer getUserId() {
		return getUser().getUserId();
	}

	protected String getUserName() {
		return getUser().getUsername();
	}

}
