package com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant;

/**
 * 任务状态
 *
 * @author liuhaipeng
 * @date 2019/7/3
 */
public enum TaskStatus {

    STARTED("已启动"),

    STOPPED("已停止"),

    FINISHED("已结束");

    private String name;

    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
