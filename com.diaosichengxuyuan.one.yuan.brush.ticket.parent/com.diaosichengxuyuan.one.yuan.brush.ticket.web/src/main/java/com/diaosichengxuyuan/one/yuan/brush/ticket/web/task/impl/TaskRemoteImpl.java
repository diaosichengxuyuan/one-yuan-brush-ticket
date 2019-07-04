package com.diaosichengxuyuan.one.yuan.brush.ticket.web.task.impl;

import com.alibaba.fastjson.JSON;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.StatusCode;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.ValidationUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task.TaskResListDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.task.TaskService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.task.TaskRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            BaseDTO baseDTO = taskService.insertTask(taskReqDTO);
            return baseDTO;
        } catch(Exception e) {
            log.error(String.format("插入task数据失败，参数：%s", JSON.toJSONString(taskReqDTO)), e);
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("提交抢票任务失败，请重试！");
            return baseDTO;
        }

    }

    @RequestMapping("/queryTaskList")
    @Override
    public TaskResListDTO queryTaskList() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskService.queryTaskList(user.getUsername());
    }

}
