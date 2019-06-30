package com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketResDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket.TicketResLstDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.ticket.TicketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhaipeng
 * @date 2019/6/30
 */
@Service("ticketService")
public class TicketServiceImpl implements TicketService {

    @Override
    public TicketResLstDTO queryTickets(TicketReqDTO ticketReqDTO) {
        List<TicketResDTO> ticketList = new ArrayList<>();
        ticketList.add(TicketResDTO.builder().trains("G101").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").build());
        ticketList.add(TicketResDTO.builder().trains("G102").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").build());
        ticketList.add(TicketResDTO.builder().trains("G103").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").build());
        ticketList.add(TicketResDTO.builder().trains("G104").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").build());
        ticketList.add(TicketResDTO.builder().trains("G105").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").build());
        ticketList.add(TicketResDTO.builder().trains("G106").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").build());
        ticketList.add(TicketResDTO.builder().trains("G107").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").build());
        ticketList.add(TicketResDTO.builder().trains("G108").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").build());
        ticketList.add(TicketResDTO.builder().trains("G109").startTime("06:43").endTime("12:40").startStation("北京南")
            .endStation("上海虹桥").costTime("5小时57分").secondSeatPrice("￥553").secondSeatRemain("21").firstSeatPrice("￥772")
            .firstSeatRemain("18").businessSeatPrice("￥1500").businessSeatRemain("0").noSeatPrice("￥200").noSeatRemain(
                "34").hardSeatPrice("￥200").hardSeatRemain("259").hardSleeperSeatPrice("￥5503").hardSleeperSeatRemain(
                "45").softSleeperSeatPrice("￥800").softSleeperSeatRemain("57").build());

        return TicketResLstDTO.builder().ticketList(ticketList).build();
    }

}
