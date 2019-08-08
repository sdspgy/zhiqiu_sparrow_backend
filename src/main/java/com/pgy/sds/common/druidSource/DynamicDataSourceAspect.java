//package com.pgy.sds.common.druidSource;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
///**
// * Author:   taoyuzhu(taoyuzhu@hulai.com)
// * Date:     2019-08-01 20:52
// * Description:
// */
//@Aspect
//@Component
//public class DynamicDataSourceAspect {
//
//	@Pointcut("@annotation(com.pgy.sds.common.druidSource.TargetDataSource)")
//	public void point() {
//	}
//
//	@Around(value = "point()")
//	public Object around(ProceedingJoinPoint pjp) throws Throwable {
//		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
//		Method targetMethod = methodSignature.getMethod();
//		if (targetMethod.isAnnotationPresent(TargetDataSource.class)) {
//			String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).dataSource();
//			DynamicDataSourceHolder.setDataSource(targetDataSource);
//		}
//		Object result = pjp.proceed();// 执行方法
//		DynamicDataSourceHolder.clearDataSource();
//		return result;
//	}
//
//}
