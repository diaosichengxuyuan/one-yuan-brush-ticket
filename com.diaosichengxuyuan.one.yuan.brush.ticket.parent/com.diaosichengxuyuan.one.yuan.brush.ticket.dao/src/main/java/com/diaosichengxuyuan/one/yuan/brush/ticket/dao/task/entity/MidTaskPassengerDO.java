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
@Table(name = "mid_task_passenger")
public class MidTaskPassengerDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "passenger_id")
    private Long passengerId;

}