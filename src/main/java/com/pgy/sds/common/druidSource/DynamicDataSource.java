package com.pgy.sds.common.druidSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 19:42
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/*根据Key返回targetDataSources*/
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSource();
	}
}