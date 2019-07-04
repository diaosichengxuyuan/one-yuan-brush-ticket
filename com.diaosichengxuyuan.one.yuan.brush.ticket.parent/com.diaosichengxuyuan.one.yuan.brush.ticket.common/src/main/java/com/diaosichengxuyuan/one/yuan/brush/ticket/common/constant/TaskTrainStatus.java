package com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant;

/**
 * 抢票任务中单个车次的状态
 *
 * @author liuhaipeng
 * @date 2019/7/3
 */
public enum TaskTrainStatus {

    NOT_SALE("未开售"),

    ON_SALE("抢票中"),

    ON_MONITOR("余票监控中"),

    STOPPED("已停止");

    private String name;

    TaskTrainStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
