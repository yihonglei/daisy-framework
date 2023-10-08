package com.jpeony.commons.core.concurrent;

import com.jpeony.commons.core.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列管理器
 *
 * @author yihonglei
 */
public class DelayQueueManager {
    private static final Logger logger = LoggerFactory.getLogger(DelayQueueManager.class);
    /**
     * 延时队列
     */
    private DelayQueue<DelayTask<?>> delayQueue;
    /**
     * 单利模式（懒汉式静态常量）
     */
    private static DelayQueueManager instance = new DelayQueueManager();

    private DelayQueueManager() {
        delayQueue = new DelayQueue<>();
        init();
    }

    public static DelayQueueManager getInstance() {
        return instance;
    }

    /**
     * 初始化
     */
    private void init() {
        /*
         * 监控线程
         */
        Thread monitorThread = new Thread(this::monitorExecute);
        monitorThread.setName("DelayQueueMonitor");
        monitorThread.start();
        logger.info("延时队列管理器初始化完成...");
    }

    /**
     * 延时队列监控逻辑
     */
    private void monitorExecute() {
        logger.info("启动了...");
        for (; ; ) {
            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            logger.info("当前存活线程数量:{}", map.size());
            int taskNum = delayQueue.size();
            logger.info("当前延时任务数量:{}", taskNum);
            try {
                DelayTask<?> delayTask = delayQueue.take();
                Runnable task = delayTask.getTask();
                logger.info("task:[{}]", task);
                if (null == task) {
                    continue;
                }
                // 任务提交线程池执行
                ThreadUtils.execute(task);
            } catch (Exception e) {
                logger.error("监控延时队列线程异常!", e);
            }
        }
    }

    /**
     * 添加任务
     *
     * @param task 延时任务
     * @param time 延时时间
     * @param unit 时间单位
     */
    public void put(Runnable task, long time, TimeUnit unit) {
        long timeout = TimeUnit.NANOSECONDS.convert(time, unit);
        DelayTask<?> delayTask = new DelayTask<>(timeout, task);
        delayQueue.put(delayTask);
    }

    /**
     * 删除任务
     */
    public boolean removeTask(DelayTask task) {
        return delayQueue.remove(task);
    }
}
