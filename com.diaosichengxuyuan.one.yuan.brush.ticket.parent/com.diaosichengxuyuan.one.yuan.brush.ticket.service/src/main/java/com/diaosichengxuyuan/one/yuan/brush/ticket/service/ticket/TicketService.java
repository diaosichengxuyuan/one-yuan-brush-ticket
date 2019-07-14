package com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.page.Page;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.AcquiredTicketResDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.AcquiredTicketResListDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketResListDTO;

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
    TicketResListDTO queryTicketList(TicketReqDTO ticketReqDTO);

    /**
     * 分页查询已抢到票列表
     *
     * @param page 分页对象
     * @return 已抢到票列表
     */
    AcquiredTicketResListDTO queryAcquiredTicketList(Page page, String accountId);

    /**
     * 根据id查询已抢到的票
     *
     * @param id 票id
     * @return 已抢到的票
     */
    AcquiredTicketResDTO queryAcquiredTicketById(Long id);

    /**
     * 根据主任务id查询已抢到的票
     *
     * @param taskId 主任务id
     * @return 已抢到的票
     */
    AcquiredTicketResDTO queryAcquiredTicketByTaskId(Long taskId);

}
