package com.diaosichengxuyuan.one.yuan.brush.ticket.service.core.executor;

/**
 * 任务执行器
 *
 * @author liuhaipeng
 * @date 2019/7/11
 */
public interface TaskExecutor {

    /**
     * 启动主任务
     *
     * @param taskId 任务id
     */
    void start(Long taskId);

    /**
     * 停止主任务
     *
     * @param taskId 任务id
     */
    void stop(Long taskId);

    /**
     * 当应用重新时，启动所有状态为已启动的任务
     */
    void startAll();

}
