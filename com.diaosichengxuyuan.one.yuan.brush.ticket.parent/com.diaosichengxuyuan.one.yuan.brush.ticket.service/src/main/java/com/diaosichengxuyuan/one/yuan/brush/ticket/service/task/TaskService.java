package com.diaosichengxuyuan.one.yuan.brush.ticket.service.task;

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
public interface TaskService {

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
     * @param id        任务id
     * @param accountId 账号
     * @return 抢票任务
     */
    TaskResDTO queryTaskById(Long id, String accountId);

    /**
     * 查询抢票任务列表
     *
     * @param accountId 账号
     * @return 任务列表
     */
    TaskResListDTO queryTaskList(String accountId);

    /**
     * 根据id删除抢票任务
     *
     * @param id        任务id
     * @param accountId 账号
     * @return 删除结果
     */
    BaseDTO deleteTaskById(Long id, String accountId);

    /**
     * 根据id停止抢票任务
     *
     * @param id        任务id
     * @param accountId 账号
     * @return 停止结果
     */
    BaseDTO stopTaskById(Long id, String accountId);

    /**
     * 启动抢票任务，先根据id更新任务，然后启动
     *
     * @param taskReqDTO 抢票任务
     * @return 启动结果
     */
    BaseDTO startTask(TaskReqDTO taskReqDTO);

}
