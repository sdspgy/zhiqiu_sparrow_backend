package com.pgy.sds.controller;

import com.pgy.sds.model.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 16:48
 * Description:
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
