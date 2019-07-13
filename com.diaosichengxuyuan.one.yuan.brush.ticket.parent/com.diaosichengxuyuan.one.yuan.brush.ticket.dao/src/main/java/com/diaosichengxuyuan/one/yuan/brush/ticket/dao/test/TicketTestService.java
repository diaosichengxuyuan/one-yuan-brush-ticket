package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author liuhaipeng
 * @date 2019/7/12
 */
@Service("ticketTestService")
public class TicketTestService {

    @Autowired
    private TicketTestMapper ticketTestMapper;

    public int queryRemainingCount(TicketTestReqDTO ticketTestReqDTO) {
        TicketTestDO ticketTestDO = ticketTestMapper.selectOne(TicketTestDO.builder().date(ticketTestReqDTO.getDate())
            .train(ticketTestReqDTO.getTrain()).build());
        if(ticketTestDO == null) {
            return 0;
        }

        return ticketTestDO.getRemain();
    }

    public boolean brushTicket(TicketTestReqDTO ticketTestReqDTO) {
        TicketTestDO ticketTestDO = ticketTestMapper.selectOne(TicketTestDO.builder().date(ticketTestReqDTO.getDate())
            .train(ticketTestReqDTO.getTrain()).build());
        if(ticketTestDO == null) {
            return false;
        }

        if(ticketTestDO.getRemain().intValue() == 0) {
            return false;
        }

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch(Exception e) {
        }

        ticketTestMapper.updateByPrimaryKeySelective(TicketTestDO.builder().id(ticketTestDO.getId())
            .remain(ticketTestDO.getRemain() - 1).build());

        return true;
    }

    public void insertOrUpdateTicket(TicketTestReqDTO ticketTestReqDTO) {
        TicketTestDO testDO = MapperUtil.map(ticketTestReqDTO, TicketTestReqDTO.class, TicketTestDO.class);

        TicketTestDO ticketTestDO = ticketTestMapper.selectOne(TicketTestDO.builder().date(ticketTestReqDTO.getDate())
            .train(ticketTestReqDTO.getTrain()).build());
        if(ticketTestDO == null) {
            testDO.setCreateTime(new Date());
            testDO.setModifyTime(new Date());
            ticketTestMapper.insert(testDO);
        } else {
            testDO.setId(ticketTestDO.getId());
            testDO.setCreateTime(ticketTestDO.getCreateTime());
            testDO.setModifyTime(new Date());
            ticketTestMapper.updateByPrimaryKeySelective(testDO);
        }
    }

}
