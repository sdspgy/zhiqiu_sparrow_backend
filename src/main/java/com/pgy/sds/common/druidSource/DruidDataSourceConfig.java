package com.pgy.sds.common.druidSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:   taoyuzhu
 * Date:     2019-08-02 09:21
 * Description:
 */
@Configuration
@MapperScan(basePackages = "com.pgy.sds.dao", sqlSessionFactoryRef = "sqlSessionFactory")
public class DruidDataSourceConfig {

	/*配置mapper的扫描，找到所有的mapper.xml映射文件*/
	@Value("${mybatis-plus.mapper-locations}")
	private String mapperLocations;

	/*数据源1*/
	@Bean(name = "commonDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.common")
	public DataSource commonDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	/*数据源2*/
	@Bean(name = "resourceDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.resource")
	public DataSource resourceDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	/*数据源管理*/
	@Bean
	public DataSource dynamicDataSource() throws SQLException {
		DynamicDataSource dynmicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("commonDataSource", commonDataSource());
		targetDataSources.put("resourceDataSource", resourceDataSource());
		dynmicDataSource.setTargetDataSources(targetDataSources);
		/*设置默认数据源*/
		dynmicDataSource.setDefaultTargetDataSource(commonDataSource());
		return dynmicDataSource;
	}

	/*SqlSessionFactory*/
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
		/*兼容Mybatis-plus的接口*/
		MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		return bean.getObject();
	}

	/*事物*/
	@Bean
	public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
