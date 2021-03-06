package com.pgy.sds.common.druidSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 19:43
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

	@Pointcut("@annotation(com.pgy.sds.common.druidSource.TargetDataSource)")
	public void point() {
	}

	@Around(value = "point()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method targetMethod = methodSignature.getMethod();
		if (targetMethod.isAnnotationPresent(TargetDataSource.class)) {
			String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).dataSource();
			DynamicDataSourceHolder.setDataSource(targetDataSource);
		}
		/*执行方法*/
		Object result = pjp.proceed();
		DynamicDataSourceHolder.clearDataSource();
		return result;
	}

}
