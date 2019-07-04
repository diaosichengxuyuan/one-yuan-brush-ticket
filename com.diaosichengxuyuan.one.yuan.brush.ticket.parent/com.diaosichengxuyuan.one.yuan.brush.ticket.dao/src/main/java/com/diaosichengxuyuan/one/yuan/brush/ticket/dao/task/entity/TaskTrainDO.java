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
@Table(name = "task_train")
public class TaskTrainDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "train")
    private String train;

    @Column(name = "start_sale_time")
    private Date startSaleTime;

    @Column(name = "end_sale_time")
    private Date endSaleTime;

    @Column(name = "status")
    private String status;

    @Column(name = "thread_id")
    private String threadId;

}