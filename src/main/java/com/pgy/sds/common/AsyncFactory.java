package com.pgy.sds.common;

import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.TimerTask;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:07
 */
@Slf4j
public class AsyncFactory {

	public static TimerTask recordLogininfo(final String username, final String status, final String messages, final Object... args) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		return new TimerTask() {

			@Override
			public void run() {
				// 获取客户端操作系统
				String os = userAgent.getOperatingSystem().getName();
				// 获取客户端浏览器
				String browser = userAgent.getBrowser().getName();
				StringBuffer loginIdd = new StringBuffer();
				loginIdd.append(os).append(":").append(browser).toString();
				String message = String.format("[姓名]：%s,[状态]：%s,[信息]：%s,[地址]：%s", username, status, messages, loginIdd);
				log.info("登录记录--" + message);
				//写库
			}
		};
	}
}
