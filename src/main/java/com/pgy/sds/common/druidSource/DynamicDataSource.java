//package com.pgy.sds.common.druidSource;
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
///**
// * Author:   taoyuzhu(taoyuzhu@hulai.com)
// * Date:     2019-08-02 09:28
// * Description:
// */
//public class DynamicDataSource extends AbstractRoutingDataSource {
//
//	/*
//	 * 根据Key返回targetDataSources
//	 */
//	@Override
//	protected Object determineCurrentLookupKey() {
//		return DynamicDataSourceHolder.getDataSource();
//	}
//}