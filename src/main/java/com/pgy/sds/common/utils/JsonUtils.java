package com.pgy.sds.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 19:47
 */
@Slf4j
public class JsonUtils {

	private final static ObjectMapper objMapper = new ObjectMapper();

	public static <T> T toObj(String jsonString, Class<T> clazz) {
		objMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			return objMapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			log.error("Json字符串转化成对象出错", e);
		}
		return null;
	}

	public static String toJson(Object obj) {
		if (obj instanceof Integer || obj instanceof Long || obj instanceof Float ||
						obj instanceof Double || obj instanceof Boolean || obj instanceof String) {
			return String.valueOf(obj);
		}
		try {
			return objMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("转化成json字符串", e);
		}
		return null;
	}
}
