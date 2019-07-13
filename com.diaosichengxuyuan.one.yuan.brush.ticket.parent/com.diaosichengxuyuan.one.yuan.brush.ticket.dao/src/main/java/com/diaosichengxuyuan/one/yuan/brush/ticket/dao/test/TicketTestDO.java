package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket_test")
public class TicketTestDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "start_sale_time")
    private Date startSaleTime;

    @Column(name = "end_sale_time")
    private Date endSaleTime;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_place")
    private String startPlace;

    @Column(name = "end_place")
    private String endPlace;

    @Column(name = "train")
    private String train;

    @Column(name = "price")
    private String price;

    @Column(name = "remain")
    private Integer remain;

}