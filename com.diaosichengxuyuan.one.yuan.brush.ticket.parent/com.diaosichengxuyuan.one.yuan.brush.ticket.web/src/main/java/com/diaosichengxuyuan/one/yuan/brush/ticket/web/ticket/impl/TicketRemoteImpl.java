package com.diaosichengxuyuan.one.yuan.brush.ticket.web.ticket.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.ValidationUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketResLstDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket.TicketService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.ticket.TicketRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuhaipeng
 * @date 2019/6/30
 */
@RestController("ticketRemote")
@RequestMapping("/ticket")
public class TicketRemoteImpl implements TicketRemote {

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/queryTickets", method = RequestMethod.POST)
    @Override
    public TicketResLstDTO queryTickets(TicketReqDTO ticketReqDTO) {
        ValidationUtil.validate(ticketReqDTO);
        return ticketService.queryTickets(ticketReqDTO);
    }

}
