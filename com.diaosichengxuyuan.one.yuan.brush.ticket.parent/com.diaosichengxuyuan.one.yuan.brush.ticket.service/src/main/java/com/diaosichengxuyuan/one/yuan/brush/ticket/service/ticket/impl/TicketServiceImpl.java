package com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.StatusCode;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.page.Page;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.DateUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.MapperUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test.TicketTestDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test.TicketTestMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.TicketDetailMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.TicketMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.entity.TicketDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.entity.TicketDetailDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.*;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket.TicketService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhaipeng
 * @date 2019/6/30
 */
@Service("ticketService")
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketDetailMapper ticketDetailMapper;

    @Autowired
    private TicketTestMapper ticketTestMapper;

    @Override
    public TicketResListDTO queryTicketList(TicketReqDTO ticketReqDTO) {
        List<TicketTestDO> ticketTestDOList = ticketTestMapper.select(TicketTestDO.builder().date(
            ticketReqDTO.getStartDate()).startPlace(ticketReqDTO.getStartPlace()).endPlace(ticketReqDTO.getEndPlace())
            .build());
        if(CollectionUtils.isEmpty(ticketTestDOList)) {
            return new TicketResListDTO();
        }

        List<TicketResDTO> ticketList = new ArrayList<>(ticketTestDOList.size());
        ticketTestDOList.forEach(ticketTestDO -> ticketList.add(TicketResDTO.builder().train(ticketTestDO.getTrain())
            .startTime("06:43").endTime("12:40").startStation(ticketTestDO.getStartPlace()).endStation(
                ticketTestDO.getEndPlace()).costTime("5小时57分").secondSeatPrice(ticketTestDO.getPrice())
            .secondSeatRemain("21").firstSeatPrice(ticketTestDO.getPrice()).firstSeatRemain("18").businessSeatPrice(
                ticketTestDO.getPrice()).businessSeatRemain("0").noSeatPrice(ticketTestDO.getPrice()).noSeatRemain("34")
            .hardSeatPrice(ticketTestDO.getPrice()).hardSeatRemain("259").hardSleeperSeatPrice(ticketTestDO.getPrice())
            .hardSleeperSeatRemain("45").softSleeperSeatPrice(ticketTestDO.getPrice()).softSleeperSeatRemain("57")
            .startSaleTime(ticketTestDO.getStartSaleTime()).endSaleTime(ticketTestDO.getEndSaleTime()).build()));

        return TicketResListDTO.builder().ticketList(ticketList).build();
    }

    @Override
    public AcquiredTicketResListDTO queryAcquiredTicketList(Page page, String accountId) {
        PageHelper.startPage(page.getNum(), page.getSize(), "modify_time desc");
        List<TicketDO> ticketDOList = ticketMapper.select(TicketDO.builder().accountId(accountId).build());
        List<AcquiredTicketResDTO> acquiredTicketResDTOList = new ArrayList<>(ticketDOList.size());
        ticketDOList.forEach(ticketDO -> acquiredTicketResDTOList.add(AcquiredTicketResDTO.builder().id(
            ticketDO.getId()).date(DateUtil.formatDate(ticketDO.getDate(), "yyyy-MM-dd")).week(
            DateUtil.toWeek(ticketDO.getWeek())).train(ticketDO.getTrain()).startPlace(ticketDO.getStartPlace())
            .endPlace(ticketDO.getEndPlace()).startTime(DateUtil.formatDate(ticketDO.getStartTime(), "HH:mm")).endTime(
                DateUtil.formatDate(ticketDO.getEndTime(), "HH:mm")).endPayTime(ticketDO.getEndPayTime())
            .status(ticketDO.getStatus()).build()));
        return AcquiredTicketResListDTO.builder().acquiredTicketResDTOList(acquiredTicketResDTOList).build();
    }

    @Override
    public AcquiredTicketResDTO queryAcquiredTicketById(Long id, String accountId) {
        TicketDO ticketDO = ticketMapper.selectOne(TicketDO.builder().id(id).accountId(accountId).build());
        if(ticketDO == null) {
            AcquiredTicketResDTO acquiredTicketResDTO = new AcquiredTicketResDTO();
            acquiredTicketResDTO.setStatusCode(StatusCode.FAILURE.getCode());
            acquiredTicketResDTO.setMessage("查询失败");
            return acquiredTicketResDTO;
        }

        List<TicketDetailDO> ticketDetailDOList = ticketDetailMapper.select(
            TicketDetailDO.builder().ticketId(ticketDO.getId()).build());

        AcquiredTicketResDTO acquiredTicketResDTO = AcquiredTicketResDTO.builder().id(ticketDO.getId()).date(
            DateUtil.formatDate(ticketDO.getDate(), "yyyy-MM-dd")).week(DateUtil.toWeek(ticketDO.getWeek())).train(
            ticketDO.getTrain()).startPlace(ticketDO.getStartPlace()).endPlace(ticketDO.getEndPlace()).startTime(
            DateUtil.formatDate(ticketDO.getStartTime(), "HH:mm")).endTime(
            DateUtil.formatDate(ticketDO.getEndTime(), "HH:mm")).endPayTime(ticketDO.getEndPayTime()).status(
            ticketDO.getStatus()).build();
        List<AcquiredTicketDetailResDTO> acquiredTicketDetailResDTOList = MapperUtil.mapAsList(ticketDetailDOList,
            TicketDetailDO.class, AcquiredTicketDetailResDTO.class);
        acquiredTicketResDTO.setAcquiredTicketDetailResDTOList(acquiredTicketDetailResDTOList);

        return acquiredTicketResDTO;
    }

    @Override
    public AcquiredTicketResDTO queryAcquiredTicketByTaskId(Long taskId, String accountId) {
        TicketDO ticketDO = ticketMapper.selectOne(TicketDO.builder().accountId(accountId).taskId(taskId).build());
        if(ticketDO == null) {
            AcquiredTicketResDTO acquiredTicketResDTO = new AcquiredTicketResDTO();
            acquiredTicketResDTO.setStatusCode(StatusCode.FAILURE.getCode());
            acquiredTicketResDTO.setMessage("查询失败");
            return acquiredTicketResDTO;
        }

        List<TicketDetailDO> ticketDetailDOList = ticketDetailMapper.select(
            TicketDetailDO.builder().ticketId(ticketDO.getId()).build());

        AcquiredTicketResDTO acquiredTicketResDTO = AcquiredTicketResDTO.builder().id(ticketDO.getId()).date(
            DateUtil.formatDate(ticketDO.getDate(), "yyyy-MM-dd")).week(DateUtil.toWeek(ticketDO.getWeek())).train(
            ticketDO.getTrain()).startPlace(ticketDO.getStartPlace()).endPlace(ticketDO.getEndPlace()).startTime(
            DateUtil.formatDate(ticketDO.getStartTime(), "HH:mm")).endTime(
            DateUtil.formatDate(ticketDO.getEndTime(), "HH:mm")).endPayTime(ticketDO.getEndPayTime()).status(
            ticketDO.getStatus()).build();
        List<AcquiredTicketDetailResDTO> acquiredTicketDetailResDTOList = MapperUtil.mapAsList(ticketDetailDOList,
            TicketDetailDO.class, AcquiredTicketDetailResDTO.class);
        acquiredTicketResDTO.setAcquiredTicketDetailResDTOList(acquiredTicketDetailResDTOList);

        return acquiredTicketResDTO;
    }

}
