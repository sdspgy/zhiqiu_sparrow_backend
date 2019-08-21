package com.pgy.sds.common;

import com.pgy.sds.common.utils.SpringUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author:   taoyuzhu
 * Date:     2019-07-11 11:41
 * Description: 异步任务管理器
 */
public class AsyncManager {

	/*操作延迟10毫秒*/
	private final int OPERATE_DELAY_TIME = 10;

	/*异步操作任务调度线程池*/
	private ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

	/*单例模式*/
	private static AsyncManager me = new AsyncManager();

	public static AsyncManager me() {
		return me;
	}

	/*执行任务*/
	public void execute(TimerTask task) {
		executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
	}

	/*停止任务线程池*/
	public void shutdown() {
		Threads.shutdownAndAwaitTermination(executor);
	}
}
