package com.diaosichengxuyuan.one.yuan.brush.ticket.web.ticket.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.page.Page;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.ValidationUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.AcquiredTicketResDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.AcquiredTicketResListDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketResLstDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket.TicketService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.ticket.TicketRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/queryTicketList", method = RequestMethod.POST)
    @Override
    public TicketResLstDTO queryTicketList(TicketReqDTO ticketReqDTO) {
        ValidationUtil.validate(ticketReqDTO);
        return ticketService.queryTicketList(ticketReqDTO);
    }

    @RequestMapping(value = "/queryAcquiredTicketList", method = RequestMethod.POST)
    @Override
    public AcquiredTicketResListDTO queryAcquiredTicketList(Page page) {
        ValidationUtil.validate(page);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ticketService.queryAcquiredTicketList(page, user.getUsername());
    }

    @RequestMapping(value = "/queryAcquiredTicketById", method = RequestMethod.GET)
    @Override
    public AcquiredTicketResDTO queryAcquiredTicketById(@RequestParam String id) {
        if(id == null) {
            throw new IllegalArgumentException("参数id不合法");
        }

        return ticketService.queryAcquiredTicketById(id);
    }

}
