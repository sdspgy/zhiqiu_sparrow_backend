package com.pgy.sds.common.druidSource;

import java.lang.annotation.*;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 19:43
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

	String dataSource();
}
