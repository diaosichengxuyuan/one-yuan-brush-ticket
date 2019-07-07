package com.diaosichengxuyuan.one.yuan.brush.ticket.web.remote.ticket;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.page.Page;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.AcquiredTicketResDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.AcquiredTicketResListDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketResLstDTO;

/**
 * 车票查询
 *
 * @author liuhaipeng
 * @date 2019/6/30
 */
public interface TicketRemote {

    /**
     * 查询车票列表
     *
     * @param ticketReqDTO 查询条件
     * @return 车票列表
     */
    TicketResLstDTO queryTicketList(TicketReqDTO ticketReqDTO);

    /**
     * 分页查询已抢到票列表
     *
     * @param page 分页对象
     * @return 已抢到票列表
     */
    AcquiredTicketResListDTO queryAcquiredTicketList(Page page);

    /**
     * 根据id查询已抢到的票
     *
     * @param id 票id
     * @return 已抢到的票
     */
    AcquiredTicketResDTO queryAcquiredTicketById(String id);

}
