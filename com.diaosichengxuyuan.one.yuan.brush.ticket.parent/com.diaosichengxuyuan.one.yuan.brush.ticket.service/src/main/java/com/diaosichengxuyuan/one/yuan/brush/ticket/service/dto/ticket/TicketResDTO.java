package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResDTO {

    private String trains;

    private String startTime;

    private String endTime;

    private String startStation;

    private String endStation;

    private String costTime;

    private String secondSeatPrice;

    private String secondSeatRemain;

    private String firstSeatPrice;

    private String firstSeatRemain;

    private String businessSeatPrice;

    private String businessSeatRemain;

    private String noSeatPrice;

    private String noSeatRemain;

    private String hardSeatPrice;

    private String hardSeatRemain;

    private String hardSleeperSeatPrice;

    private String hardSleeperSeatRemain;

    private String softSleeperSeatPrice;

    private String softSleeperSeatRemain;

}
