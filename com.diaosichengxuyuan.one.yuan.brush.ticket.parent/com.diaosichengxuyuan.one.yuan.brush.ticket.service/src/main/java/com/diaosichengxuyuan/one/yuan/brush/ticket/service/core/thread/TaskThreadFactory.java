package com.diaosichengxuyuan.one.yuan.brush.ticket.service.core.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 *
 * @author liuhaipeng
 * @date 2019/7/11
 */
public class TaskThreadFactory implements ThreadFactory {
    private final ThreadGroup group;

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private static final String NAME_PREFIX = "one-yuan-brush-ticket-task-thread-";

    private static final ThreadFactory INSTANCE = new TaskThreadFactory();

    public static ThreadFactory getInstance() {
        return INSTANCE;
    }

    private TaskThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, NAME_PREFIX + threadNumber.getAndIncrement(), 0);
        if(t.isDaemon()) {
            t.setDaemon(false);
        }
        if(t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
