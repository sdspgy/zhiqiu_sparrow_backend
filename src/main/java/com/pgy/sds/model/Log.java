package com.pgy.sds.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 19:24
 * Description:
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

	String value() default "";
}

