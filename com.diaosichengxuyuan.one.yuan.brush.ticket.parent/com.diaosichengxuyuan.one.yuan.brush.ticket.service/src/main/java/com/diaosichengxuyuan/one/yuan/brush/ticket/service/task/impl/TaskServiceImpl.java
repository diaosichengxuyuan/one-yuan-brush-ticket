package com.diaosichengxuyuan.one.yuan.brush.ticket.service.task.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.StatusCode;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.TaskStatus;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.TaskTrainStatus;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.DateUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.MapperUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.MidTaskPassengerMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.TaskMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.TaskTrainMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.MidTaskPassengerDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskTrainDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResListDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.task.TaskService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuhaipeng
 * @date 2019/7/4
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskTrainMapper taskTrainMapper;

    @Autowired
    private MidTaskPassengerMapper midTaskPassengerMapper;

    @Value("${brush.ticket.task.max.count}")
    private int taskMaxCount;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseDTO insertTask(TaskReqDTO taskReqDTO) {
        int count = taskMapper.selectCount(TaskDO.builder().accountId(taskReqDTO.getAccountId()).build());
        if(count >= taskMaxCount) {
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("任务个数已达上限，请到抢票任务列表页面删除！");
            return baseDTO;
        }

        TaskDO taskDO = TaskDO.builder().createTime(new Date()).modifyTime(new Date()).accountId(
            taskReqDTO.getAccountId()).startPlace(taskReqDTO.getStartPlace()).endPlace(taskReqDTO.getEndPlace())
            .startDate(taskReqDTO.getStartDate()).seat(taskReqDTO.getSeat()).phone(taskReqDTO.getPhone()).status(
                TaskStatus.STARTED.getName()).build();

        //插入task表
        taskMapper.insertSelective(taskDO);

        //插入task_train表
        taskReqDTO.getTaskTrainReqDTOList().forEach(
            taskTrainReqDTO -> taskTrainMapper.insertSelective(TaskTrainDO.builder().createTime(new Date()).modifyTime(
                new Date()).taskId(taskDO.getId()).train(taskTrainReqDTO.getTrain()).startSaleTime(
                taskTrainReqDTO.getStartSaleTime()).endSaleTime(taskTrainReqDTO.getEndSaleTime())
                .status(TaskTrainStatus.NOT_SALE.getName()).build()));

        //插入mid_task_passenger表
        taskReqDTO.getPassengerIdList().forEach(
            passengerId -> midTaskPassengerMapper.insertSelective(MidTaskPassengerDO.builder().createTime(new Date())
                .modifyTime(new Date()).taskId(taskDO.getId()).passengerId(passengerId).build()));

        return new BaseDTO();
    }

    @Override
    public TaskResListDTO queryTaskList(String accountId) {
        PageHelper.startPage(0, taskMaxCount);
        List<TaskDO> taskDOs = taskMapper.select(TaskDO.builder().accountId(accountId).build());
        List<TaskResDTO> taskResDTOList = new ArrayList<>(taskDOs.size());
        taskDOs.forEach(taskDO -> {
            taskResDTOList.add(TaskResDTO.builder().id(taskDO.getId()).startPlace(taskDO.getStartPlace()).endPlace(
                taskDO.getEndPlace()).startDate(DateUtil.formatDate(taskDO.getStartDate(), "yyyy年MM月dd日"))
                .status(taskDO.getStatus()).build());
        });
        return TaskResListDTO.builder().taskResDTOList(taskResDTOList).build();
    }

}
