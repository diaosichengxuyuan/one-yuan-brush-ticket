package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liuhaipeng
 * @date 2019/7/12
 */
@Service("ticketTestService")
public class TicketTestService {

    @Autowired
    private TicketTestMapper ticketTestMapper;

    /**
     * 查询余票数量
     */
    public int queryRemainingCount(TicketTestReqDTO ticketTestReqDTO) {
        TicketTestDO ticketTestDO = ticketTestMapper.selectOne(TicketTestDO.builder().date(ticketTestReqDTO.getDate())
            .train(ticketTestReqDTO.getTrain()).build());
        if(ticketTestDO == null) {
            return 0;
        }

        return ticketTestDO.getRemain();
    }

    /**
     * 刷票，此处有并发更改数据库的情况
     */
    public boolean brushTicket(TicketTestReqDTO ticketTestReqDTO) {
        TicketTestDO ticketTestDO = ticketTestMapper.selectOne(TicketTestDO.builder().date(ticketTestReqDTO.getDate())
            .train(ticketTestReqDTO.getTrain()).build());
        if(ticketTestDO == null) {
            return false;
        }

        if(ticketTestDO.getRemain().intValue() == 0) {
            return false;
        }

        //使用乐观锁带version去更新
        int num = ticketTestMapper.updateByPrimaryKeyAndRemain(ticketTestDO.getRemain() - 1, ticketTestDO.getId(),
            ticketTestDO.getRemain());
        if(num == 0) {
            return false;
        }

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch(Exception e) {
        }

        return true;
    }

}
