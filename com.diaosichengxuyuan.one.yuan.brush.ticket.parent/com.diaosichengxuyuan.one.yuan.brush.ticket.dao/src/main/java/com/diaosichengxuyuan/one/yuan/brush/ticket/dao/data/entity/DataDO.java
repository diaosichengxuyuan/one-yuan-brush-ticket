package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.data.entity;

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
@Table(name = "data")
public class DataDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "task_num")
    private Long taskNum;

    @Column(name = "user_num")
    private Long userNum;

    @Column(name = "member_num")
    private Long memberNum;

}