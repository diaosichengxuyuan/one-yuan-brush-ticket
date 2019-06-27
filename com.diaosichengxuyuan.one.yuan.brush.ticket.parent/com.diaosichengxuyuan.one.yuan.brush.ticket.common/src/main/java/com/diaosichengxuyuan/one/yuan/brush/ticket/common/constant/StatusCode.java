package com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant;

import lombok.Data;

/**
 * 状态码
 *
 * @author liuhaipeng
 * @date 2019/6/18
 */
public enum StatusCode {

    SUCCESS("200"),
    FAILURE("500");

    private String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
