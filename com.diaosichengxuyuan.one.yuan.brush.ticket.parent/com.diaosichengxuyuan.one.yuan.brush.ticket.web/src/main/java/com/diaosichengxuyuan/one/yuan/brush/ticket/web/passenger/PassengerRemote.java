package com.diaosichengxuyuan.one.yuan.brush.ticket.web.passenger;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.passenger.PassengerListDTO;

/**
 * 乘客信息查询
 *
 * @author liuhaipeng
 * @date 2019/7/3
 */
public interface PassengerRemote {

    /**
     * 查询乘客信息
     *
     * @return 乘客信息
     */
    PassengerListDTO queryPassengers();

}
