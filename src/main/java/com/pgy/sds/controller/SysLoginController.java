package com.pgy.sds.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pgy.sds.common.AsyncFactory;
import com.pgy.sds.common.AsyncManager;
import com.pgy.sds.common.constant.SysConstants;
import com.pgy.sds.common.utils.MessageUtils;
import com.pgy.sds.dao.SysUserMapper;
import com.pgy.sds.model.*;
import com.pgy.sds.service.SysUserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:24
 */
@Slf4j
@RestController
public class SysLoginController extends AbstractController {

	@Autowired SysUserMapper sysUserMapper;

	@Autowired SysUserTokenService sysUserTokenService;

	@Value(value = "${user.password.maxRetryCount}")
	private String maxRetryCount;

	@PostMapping("/admin/sys/login")
	public Result login(@RequestBody @Valid SysLoginForm form, BindingResult errorResult) {
		if (errorResult.hasErrors()) {
			List<ObjectError> errors = errorResult.getAllErrors();
			List<String> messAges = errors.stream().map(info -> info.getDefaultMessage()).collect(Collectors.toList());
			String msg = StringUtils.collectionToDelimitedString(messAges, ",");
			return Result.error(msg);
		}
		//用户信息
		SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>()
						.lambda()
						.eq(SysUser::getUsername, form.getUsername()));
		if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			//用户名或密码错误
			AsyncManager.me().execute(AsyncFactory.recordLogininfo(user.getUsername(), "fail", MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
			return Result.error(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
		}
		if (user.getStatus().equals(SysConstants.ACCOUNT_LOCKING)) {
			return Result.error(MessageUtils.message("user.blocked"));
		}

		//生成token，并保存到redis
		return sysUserTokenService.createToken(user.getUserId());
	}

}
