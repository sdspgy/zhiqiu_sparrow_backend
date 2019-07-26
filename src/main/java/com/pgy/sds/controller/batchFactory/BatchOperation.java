package com.pgy.sds.controller.batchFactory;

import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-26 11:22
 * Description: 批量操作
 */
public interface BatchOperation {

	public void operation(List<Integer> list);
}
