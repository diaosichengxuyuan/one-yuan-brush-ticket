package com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketResLstDTO;

/**
 * 车票查询
 *
 * @author liuhaipeng
 * @date 2019/6/30
 */
public interface TicketService {

    /**
     * 查询车票列表
     *
     * @param ticketReqDTO 查询条件
     * @return 车票列表
     */
    TicketResLstDTO queryTickets(TicketReqDTO ticketReqDTO);

}
