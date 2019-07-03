package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity;

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
@Table(name = "task")
public class TaskDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "start_place")
    private String startPlace;

    @Column(name = "end_place")
    private String endPlace;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "seat")
    private String seat;

    @Column(name = "phone")
    private String phone;

}