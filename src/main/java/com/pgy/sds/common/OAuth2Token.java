package com.pgy.sds.common;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:09
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
