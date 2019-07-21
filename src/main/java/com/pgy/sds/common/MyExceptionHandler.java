package com.pgy.sds.common;

import com.pgy.sds.model.ErrorEnum;
import com.pgy.sds.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 20:59
 * Description:
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

	@ExceptionHandler(MyException.class)
	public Result handleMyException(MyException e) {
		Result result = new Result();
		result.put("code", e.getCode());
		result.put("msg", e.getMsg());
		return result;
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e) {
		log.error(e.getMessage(), e);
		return Result.exception();
	}

	@ExceptionHandler(AuthorizationException.class)
	public Result handleAuthorizationException(AuthorizationException e) {
		log.error(e.getMessage(), e);
		return Result.exception(ErrorEnum.NO_AUTH);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Result handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return Result.exception(ErrorEnum.PATH_NOT_FOUND);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException e) {
		log.error(e.getMessage(), e);
		return Result.exception(ErrorEnum.DUPLICATE_KEY);
	}
}
