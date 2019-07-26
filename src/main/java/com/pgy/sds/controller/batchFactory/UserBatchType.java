package com.pgy.sds.controller.batchFactory;

import com.pgy.sds.common.utils.SpringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-26 11:28
 * Description:
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
