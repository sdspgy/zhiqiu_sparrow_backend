package com.pgy.sds.model;

import java.util.HashMap;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 09:36
 * Description:
 */
public class Result extends HashMap<String, Object> {

	public Result() {
		put("code", 200);
		put("msg", "success");
	}

	public static Result ok() {
		return new Result();
	}

	public static Result error() {
		return error(ErrorEnum.UNKNOWN);
	}

	public static Result error(ErrorEnum eEnum) {
		return new Result().put("code", eEnum.getCode()).put("msg", eEnum.getMsg());
	}

	public static Result error(String msg) {
		return new Result().put("msg",msg).put("code", ErrorEnum.UNKNOWN.getCode());
	}

	public static Result error(Integer code , String msg){
		return new Result().put("code",code).put("msg",msg);
	}

	public static Result exception() {
		return exception(ErrorEnum.UNKNOWN);
	}

	public static Result exception(ErrorEnum eEnum) {
		return new Result().put("code", eEnum.getCode()).put("msg", eEnum.getMsg());
	}



	/**
	 * 封装业务数据
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		//将HashMap对象本身返回
		return this;
	}
}
