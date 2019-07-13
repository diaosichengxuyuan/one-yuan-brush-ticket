package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task;

import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TaskMapper extends Mapper<TaskDO> {

    List<TaskDO> selectAllByStatus(@Param("status") String status);

}