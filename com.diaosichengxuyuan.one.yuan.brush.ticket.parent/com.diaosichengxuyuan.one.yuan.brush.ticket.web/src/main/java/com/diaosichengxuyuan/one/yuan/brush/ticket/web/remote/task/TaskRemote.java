package com.diaosichengxuyuan.one.yuan.brush.ticket.web.remote.task;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResDTO;
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
     * 根据id查询抢票任务
     *
     * @param id 任务id
     * @return 抢票任务
     */
    TaskResDTO queryTaskById(Long id);

    /**
     * 查询抢票任务列表
     *
     * @return 任务列表
     */
    TaskResListDTO queryTaskList();

    /**
     * 根据id删除抢票任务
     *
     * @param id 任务id
     * @return 删除结果
     */
    BaseDTO deleteTaskById(Long id);

    /**
     * 根据id停止抢票任务
     *
     * @param id 任务id
     * @return 停止结果
     */
    BaseDTO stopTaskById(Long id);

    /**
     * 启动抢票任务，先根据id更新任务，然后启动
     *
     * @param taskReqDTO 抢票任务
     * @return 启动结果
     */
    BaseDTO startTask(TaskReqDTO taskReqDTO);

}
