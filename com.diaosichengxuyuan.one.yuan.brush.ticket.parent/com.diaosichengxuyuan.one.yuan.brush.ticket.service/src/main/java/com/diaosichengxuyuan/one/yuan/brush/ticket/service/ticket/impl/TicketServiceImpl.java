package com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.StatusCode;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.page.Page;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.DateUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.MapperUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.TicketDetailMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.TicketMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.entity.TicketDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.entity.TicketDetailDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.*;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket.TicketService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public TicketResLstDTO queryTicketList(TicketReqDTO ticketReqDTO) {
        List<TicketResDTO> ticketList = new ArrayList<>();
        ticketList.add(TicketResDTO.builder().train("G101").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").startSaleTime(
                DateUtil.addDays(new Date(), -1)).endSaleTime(DateUtil.addDays(new Date(), 1)).build());
        ticketList.add(TicketResDTO.builder().train("G102").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").startSaleTime(
                DateUtil.addDays(new Date(), -1)).endSaleTime(DateUtil.addDays(new Date(), 1)).build());
        ticketList.add(TicketResDTO.builder().train("G103").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").startSaleTime(
                DateUtil.addDays(new Date(), -1)).endSaleTime(DateUtil.addDays(new Date(), 1)).build());
        ticketList.add(TicketResDTO.builder().train("G104").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").startSaleTime(
                DateUtil.addDays(new Date(), -1)).endSaleTime(DateUtil.addDays(new Date(), 1)).build());
        ticketList.add(TicketResDTO.builder().train("G105").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").startSaleTime(
                DateUtil.addDays(new Date(), -1)).endSaleTime(DateUtil.addDays(new Date(), 1)).build());
        ticketList.add(TicketResDTO.builder().train("G106").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").startSaleTime(
                DateUtil.addDays(new Date(), -1)).endSaleTime(DateUtil.addDays(new Date(), 1)).build());
        ticketList.add(TicketResDTO.builder().train("G107").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").startSaleTime(
                DateUtil.addDays(new Date(), -1)).endSaleTime(DateUtil.addDays(new Date(), 1)).build());
        ticketList.add(TicketResDTO.builder().train("G108").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").startSaleTime(
                DateUtil.addDays(new Date(), -1)).endSaleTime(DateUtil.addDays(new Date(), 1)).build());
        ticketList.add(TicketResDTO.builder().train("G109").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").startSaleTime(
                DateUtil.addDays(new Date(), -1)).endSaleTime(DateUtil.addDays(new Date(), 1)).build());

        return TicketResLstDTO.builder().ticketList(ticketList).build();
    }

    @Override
    public AcquiredTicketResListDTO queryAcquiredTicketList(Page page, String accountId) {
        PageHelper.startPage(page.getNum(), page.getSize());
        List<TicketDO> ticketDOList = ticketMapper.select(TicketDO.builder().accountId(accountId).build());
        List<AcquiredTicketResDTO> acquiredTicketResDTOList = new ArrayList<>(ticketDOList.size());
        ticketDOList.forEach(ticketDO -> acquiredTicketResDTOList.add(AcquiredTicketResDTO.builder().id(
            ticketDO.getId()).date(DateUtil.formatDate(ticketDO.getDate(), "yyyy-MM-dd")).week(
            DateUtil.toWeek(ticketDO.getWeek())).train(ticketDO.getTrain()).startPlace(ticketDO.getStartPlace())
            .endPlace(ticketDO.getEndPlace()).startTime(DateUtil.formatDate(ticketDO.getStartTime(), "HH:mm")).endTime(
                DateUtil.formatDate(ticketDO.getEndTime(), "HH:mm")).status(ticketDO.getStatus()).build()));
        return AcquiredTicketResListDTO.builder().acquiredTicketResDTOList(acquiredTicketResDTOList).build();
    }

    @Override
    public AcquiredTicketResDTO queryAcquiredTicketById(String id) {
        TicketDO ticketDO = ticketMapper.selectByPrimaryKey(id);
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
            DateUtil.formatDate(ticketDO.getEndTime(), "HH:mm")).status(ticketDO.getStatus()).build();
        List<AcquiredTicketDetailResDTO> acquiredTicketDetailResDTOList = MapperUtil.mapAsList(ticketDetailDOList,
            TicketDetailDO.class, AcquiredTicketDetailResDTO.class);
        acquiredTicketResDTO.setAcquiredTicketDetailResDTOList(acquiredTicketDetailResDTOList);

        return acquiredTicketResDTO;
    }

}
