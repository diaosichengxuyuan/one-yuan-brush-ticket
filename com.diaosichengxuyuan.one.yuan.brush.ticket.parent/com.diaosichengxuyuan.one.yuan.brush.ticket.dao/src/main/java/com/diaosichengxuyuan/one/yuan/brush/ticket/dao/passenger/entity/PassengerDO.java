package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger.entity;

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
@Table(name = "passenger")
public class PassengerDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "attribute")
    private String attribute;

    @Column(name = "id_card")
    private String idCard;

}