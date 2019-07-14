package com.diaosichengxuyuan.one.yuan.brush.ticket.service.task.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.StatusCode;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.TaskStatus;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.TaskTrainStatus;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.DateUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.MapperUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger.PassengerMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger.entity.PassengerDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.MidTaskPassengerMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.TaskMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.TaskTrainMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.MidTaskPassengerDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskTrainDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.core.executor.TaskExecutor;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.data.DataService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.passenger.PassengerDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResListDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskTrainResDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.task.TaskService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private DataService dataService;

    @Value("${brush.ticket.task.max.count}")
    private int taskMaxCount;

    @Autowired
    private TaskExecutor taskExecutor;

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

        //插入到数据表
        dataService.increaseTaskNumber();

        Long id = internalInsertTask(taskReqDTO);

        //启动所有相关task_train
        taskExecutor.start(id);

        return new BaseDTO();
    }

    private Long internalInsertTask(TaskReqDTO taskReqDTO) {
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

        return taskDO.getId();
    }

    @Override
    public TaskResDTO queryTaskById(Long id, String accountId) {
        TaskDO taskDO = taskMapper.selectOne(TaskDO.builder().id(id).accountId(accountId).build());
        if(taskDO == null) {
            TaskResDTO taskResDTO = new TaskResDTO();
            taskResDTO.setStatusCode(StatusCode.FAILURE.getCode());
            taskResDTO.setMessage("查询失败");
            return taskResDTO;
        }

        TaskResDTO taskResDTO = MapperUtil.map(taskDO, TaskDO.class, TaskResDTO.class);
        taskResDTO.setStartDate(DateUtil.formatDate(taskDO.getStartDate(), "yyyy-MM-dd"));

        List<TaskTrainDO> taskTrainDOList = taskTrainMapper.select(TaskTrainDO.builder().taskId(id).build());
        List<TaskTrainResDTO> taskTrainResDTOList = MapperUtil.mapAsList(taskTrainDOList, TaskTrainDO.class,
            TaskTrainResDTO.class);
        taskResDTO.setTaskTrainResDTOList(taskTrainResDTOList);

        List<MidTaskPassengerDO> midTaskPassengerDOList = midTaskPassengerMapper.select(
            MidTaskPassengerDO.builder().taskId(id).build());
        List<Long> passengerIds = midTaskPassengerDOList.stream().map(
            midTaskPassengerDO -> midTaskPassengerDO.getPassengerId()).collect(Collectors.toList());

        List<PassengerDO> passengerDOList = passengerMapper.queryListByIds(passengerIds);
        List<PassengerDTO> passengerDTOList = MapperUtil.mapAsList(passengerDOList, PassengerDO.class,
            PassengerDTO.class);
        taskResDTO.setPassengerDTOList(passengerDTOList);

        return taskResDTO;
    }

    @Override
    public TaskResListDTO queryTaskList(String accountId) {
        PageHelper.startPage(1, taskMaxCount, "modify_time desc");
        List<TaskDO> taskDOList = taskMapper.select(TaskDO.builder().accountId(accountId).build());
        List<TaskResDTO> taskResDTOList = new ArrayList<>(taskDOList.size());
        taskDOList.forEach(taskDO -> taskResDTOList.add(TaskResDTO.builder().id(taskDO.getId()).startPlace(
            taskDO.getStartPlace()).endPlace(taskDO.getEndPlace()).startDate(
            DateUtil.formatDate(taskDO.getStartDate(), "yyyy年MM月dd日")).status(taskDO.getStatus()).build()));
        return TaskResListDTO.builder().taskResDTOList(taskResDTOList).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseDTO deleteTaskById(Long id, String accountId) {
        TaskDO taskDO = taskMapper.selectOne(TaskDO.builder().id(id).accountId(accountId).build());
        if(taskDO == null) {
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("查询失败");
            return baseDTO;
        }

        if(TaskStatus.STARTED.getName().equals(taskDO.getStatus())) {
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("请先停止该任务！");
            return baseDTO;
        }

        internalDeleteTaskById(id);

        return new BaseDTO();
    }

    private void internalDeleteTaskById(Long id) {
        //删除task_train
        taskTrainMapper.delete(TaskTrainDO.builder().taskId(id).build());

        //删除mid_task_passenger
        midTaskPassengerMapper.delete(MidTaskPassengerDO.builder().taskId(id).build());

        //删除task
        taskMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseDTO stopTaskById(Long id, String accountId) {
        TaskDO taskDO = taskMapper.selectOne(TaskDO.builder().id(id).accountId(accountId).build());
        if(taskDO == null) {
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("查询失败");
            return baseDTO;
        }

        if(!TaskStatus.STARTED.getName().equals(taskDO.getStatus())) {
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("不能停止该任务！");
            return baseDTO;
        }

        //停止所有相关task_train
        taskExecutor.stop(id);

        taskMapper.updateByPrimaryKeySelective(TaskDO.builder().id(taskDO.getId()).modifyTime(new Date())
            .status(TaskStatus.STOPPED.getName()).build());

        return new BaseDTO();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseDTO startTask(TaskReqDTO taskReqDTO) {
        TaskDO taskDO = taskMapper.selectOne(TaskDO.builder().id(taskReqDTO.getId())
            .accountId(taskReqDTO.getAccountId()).build());
        if(taskDO == null) {
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("查询失败");
            return baseDTO;
        }

        if(!TaskStatus.STOPPED.getName().equals(taskDO.getStatus())) {
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("不能启动该任务！");
            return baseDTO;
        }

        internalDeleteTaskById(taskReqDTO.getId());

        Long id = internalInsertTask(taskReqDTO);

        //启动所有相关task_train
        taskExecutor.start(id);

        return new BaseDTO();
    }

}
