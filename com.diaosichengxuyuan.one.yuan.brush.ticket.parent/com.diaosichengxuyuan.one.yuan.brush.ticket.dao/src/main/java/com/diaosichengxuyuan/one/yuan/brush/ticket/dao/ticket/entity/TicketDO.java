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
@Table(name = "ticket")
public class TicketDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "date")
    private Date date;

    @Column(name = "week")
    private Byte week;

    @Column(name = "train")
    private String train;

    @Column(name = "start_place")
    private String startPlace;

    @Column(name = "end_place")
    private String endPlace;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "end_pay_time")
    private Date endPayTime;

    @Column(name = "status")
    private String status;

    @Column(name = "task_train_id")
    private Long taskTrainId;

}