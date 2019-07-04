package com.diaosichengxuyuan.one.yuan.brush.ticket.web.task;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResListDTO;

/**
 * 抢票任务
 *
 * @author liuhaipeng
 * @date 2019/7/4
 */
public interface TaskRemote {

    /**
     * 插入抢票任务
     *
     * @param taskReqDTO 抢票任务
     * @return 插入结果
     */
    BaseDTO insertTask(TaskReqDTO taskReqDTO);

    /**
     * 查询抢票任务列表
     *
     * @return 任务列表
     */
    TaskResListDTO queryTaskList();

}
