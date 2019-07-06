package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ticket_detail")
public class TicketDetailDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "name")
    private String name;

    @Column(name = "attribute")
    private String attribute;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "seat")
    private String seat;

    @Column(name = "carriage")
    private String carriage;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "price")
    private String price;

}