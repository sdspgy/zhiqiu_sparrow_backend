package com.pgy.sds.common.druidSource;

import java.lang.annotation.*;

/**
 * Author:   taoyuzhu
 * Date:     2019-08-02 09:29
 * Description:
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

	String dataSource();
}
