package com.pgy.sds.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 19:47
 */
@Component
public final class SpringUtils implements BeanFactoryPostProcessor {

	/*Spring应用上下文环境*/
	private static ConfigurableListableBeanFactory beanFactory;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
		SpringUtils.beanFactory = configurableListableBeanFactory;
	}

	/**
	 * 获取对象
	 *
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws org.springframework.beans.BeansException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) beanFactory.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象
	 *
	 * @param clazz
	 * @return
	 * @throws org.springframework.beans.BeansException
	 */
	public static <T> T getBean(Class<T> clazz) throws BeansException {
		T result = (T) beanFactory.getBean(clazz);
		return result;
	}

}
