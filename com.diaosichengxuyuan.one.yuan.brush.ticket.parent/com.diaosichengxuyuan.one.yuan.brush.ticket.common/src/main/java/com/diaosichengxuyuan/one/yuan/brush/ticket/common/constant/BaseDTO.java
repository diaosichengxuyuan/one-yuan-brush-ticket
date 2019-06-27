package com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant;

import lombok.Data;

/**
 * 所有DTO的父类
 *
 * @author liuhaipeng
 * @date 2019/6/18
 */
@Data
public class BaseDTO {

    /**
     * 状态码
     */
    private String statusCode = StatusCode.SUCCESS.getCode();

    /**
     * 成功或失败的描述
     */
    private String message;
}
