package com.diaosichengxuyuan.one.yuan.brush.ticket.web.task.impl;

import com.alibaba.fastjson.JSON;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.StatusCode;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.ValidationUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResListDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.task.TaskService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.task.TaskRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuhaipeng
 * @date 2019/7/4
 */
@Slf4j
@RestController("taskRemote")
@RequestMapping("/task")
public class TaskRemoteImpl implements TaskRemote {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "insertTask", method = RequestMethod.POST)
    @Override
    public BaseDTO insertTask(TaskReqDTO taskReqDTO) {
        ValidationUtil.validate(taskReqDTO);

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskReqDTO.setAccountId(user.getUsername());

        try {
            return taskService.insertTask(taskReqDTO);
        } catch(Exception e) {
            log.error(String.format("提交抢票任务失败，参数：%s", JSON.toJSONString(taskReqDTO)), e);
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("提交抢票任务失败，请重试！");
            return baseDTO;
        }
    }

    @RequestMapping(value = "/queryTaskById", method = RequestMethod.GET)
    @Override
    public TaskResDTO queryTaskById(@RequestParam Long id) {
        if(id == null) {
            throw new IllegalArgumentException("参数id不合法");
        }

        return taskService.queryTaskById(id);
    }

    @RequestMapping(value = "/queryTaskList", method = RequestMethod.GET)
    @Override
    public TaskResListDTO queryTaskList() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskService.queryTaskList(user.getUsername());
    }

    @RequestMapping(value = "/deleteTaskById", method = RequestMethod.POST)
    @Override
    public BaseDTO deleteTaskById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("参数id不合法");
        }

        try {
            return taskService.deleteTaskById(id);
        } catch(Exception e) {
            log.error(String.format("删除抢票任务失败，参数：%s", id), e);
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("删除抢票任务失败，请重试！");
            return baseDTO;
        }
    }

    @RequestMapping(value = "/stopTaskById", method = RequestMethod.POST)
    @Override
    public BaseDTO stopTaskById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("参数id不合法");
        }

        try {
            return taskService.stopTaskById(id);
        } catch(Exception e) {
            log.error(String.format("停止抢票任务失败，参数：%s", id), e);
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("停止抢票任务失败，请重试！");
            return baseDTO;
        }
    }

    @RequestMapping(value = "/startTaskById", method = RequestMethod.POST)
    @Override
    public BaseDTO startTask(TaskReqDTO taskReqDTO) {
        ValidationUtil.validate(taskReqDTO);

        if(taskReqDTO.getId() == null) {
            throw new IllegalArgumentException("参数id不合法");
        }

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        taskReqDTO.setAccountId(user.getUsername());

        try {
            return taskService.startTask(taskReqDTO);
        } catch(Exception e) {
            log.error(String.format("启动抢票任务失败，参数：%s", JSON.toJSONString(taskReqDTO)), e);
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("启动抢票任务失败，请重试！");
            return baseDTO;
        }
    }

}
