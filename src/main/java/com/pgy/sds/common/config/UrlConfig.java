package com.pgy.sds.common.config;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 19:43
 */
@Configuration
public class UrlConfig {

	@Bean
	public ServletWebServerFactory servletContainer() {

		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
		return tomcat;
	}
}
