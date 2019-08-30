package com.pgy.sds.common.utils;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:00
 */
public class StringUtils extends org.springframework.util.StringUtils {

	public static boolean isBlank(final CharSequence cs) {
		return !StringUtils.isNotBlank(cs);
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return StringUtils.hasText(cs);
	}

}
