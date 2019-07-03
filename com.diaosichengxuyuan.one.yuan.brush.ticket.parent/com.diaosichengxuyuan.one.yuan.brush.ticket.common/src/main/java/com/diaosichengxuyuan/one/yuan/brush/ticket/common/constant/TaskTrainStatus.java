package com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant;

/**
 * 抢票任务中单个车次的状态
 *
 * @author liuhaipeng
 * @date 2019/7/3
 */
public enum TaskTrainStatus {

    /**
     * 未开售
     */
    NOT_SALE,

    /**
     * 抢票中
     */
    ON_SALE,

    /**
     * 余票监控中
     */
    ON_MONITOR,

    /**
     * 已停止
     */
    STOPPED

}
