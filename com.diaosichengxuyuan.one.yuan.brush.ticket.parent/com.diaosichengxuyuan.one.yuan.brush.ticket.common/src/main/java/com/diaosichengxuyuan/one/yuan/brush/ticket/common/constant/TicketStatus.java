package com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant;

/**
 * 车票状态
 *
 * @author liuhaipeng
 * @date 2019/7/3
 */
public enum TicketStatus {

    NOT_PAY("未支付"),

    HAS_PAY("已支付"),

    CANCELLED("已取消");

    private String name;

    TicketStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
