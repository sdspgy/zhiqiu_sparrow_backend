package com.pgy.sds.common;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 10:20
 * Description:
 */
public class OAuth2Token implements AuthenticationToken {

	private String token;

	public OAuth2Token(String token) {
		this.token = token;
	}

	@Override
	public String getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}
