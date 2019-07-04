package com.diaosichengxuyuan.one.yuan.brush.ticket.service.passenger;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.passenger.PassengerListDTO;

/**
 * 乘客信息查询
 *
 * @author liuhaipeng
 * @date 2019/7/3
 */
public interface PassengerService {

    /**
     * 查询乘客信息
     *
     * @param accountId 账号
     * @return 乘客信息
     */
    PassengerListDTO queryPassengers(String accountId);

}
