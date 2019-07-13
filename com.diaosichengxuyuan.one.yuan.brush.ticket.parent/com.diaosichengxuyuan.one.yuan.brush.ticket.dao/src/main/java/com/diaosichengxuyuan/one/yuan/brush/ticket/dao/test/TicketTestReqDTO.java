package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liuhaipeng
 * @date 2019/7/11
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketTestReqDTO {

    private Date startSaleTime;

    private Date endSaleTime;

    private Date date;

    private String startPlace;

    private String endPlace;

    private String train;

    private String price;

    private Integer remain;

}
