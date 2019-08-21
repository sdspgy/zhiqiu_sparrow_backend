package com.pgy.sds.common.utils;

/**
 * Author:   taoyuzhu
 * Date:     2019-07-10 19:54
 * Description:
 */
public class StringUtils extends org.springframework.util.StringUtils {

	public static boolean isBlank(final CharSequence cs) {
		return !StringUtils.isNotBlank(cs);
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return StringUtils.hasText(cs);
	}

}
