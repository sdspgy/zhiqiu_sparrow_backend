package com.pgy.sds.controller;

import com.pgy.sds.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 20:16
 * Description:
 */
@Aspect
@Component
@Slf4j
public class CotrollerAspect {

	@Pointcut("execution( public java.util.Map com.pgy.sds.controller.*.*(..))")
	public void aop() {

	}

	@Around("aop()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, Object> result = new HashMap<>();
		try {
			result.putAll((Map<String, Object>) joinPoint.proceed());
			return result;
		} catch (Throwable e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		log.info(JsonUtils.toJson(result));
		return result;
	}
}

