package com.pgy.sds.controller.batchFactory;

import com.pgy.sds.common.utils.SpringUtils;
import lombok.Getter;

import java.util.List;

/**
 * Author:         知秋
 * CreateDate:     2019-08-30 20:24
 */
@Getter
public enum UserBatchType {

	up(0, "修改") {
		@Override
		public void batch(List<Integer> list) {
			SpringUtils.getBean(UserUpdate.class).operation(list);
		}
	},
	de(1, "删除") {
		@Override
		public void batch(List<Integer> list) {
			SpringUtils.getBean(UserDel.class).operation(list);
		}
	};

	private int code;
	private String type;

	UserBatchType(int code, String type) {
		this.code = code;
		this.type = type;
	}

	public abstract void batch(List<Integer> list);

	public static UserBatchType getUserBatchTypeAsCode(int code) {
		for (UserBatchType value : UserBatchType.values()) {
			if (value.code == code) {
				return value;
			}
		}
		return null;
	}
}
