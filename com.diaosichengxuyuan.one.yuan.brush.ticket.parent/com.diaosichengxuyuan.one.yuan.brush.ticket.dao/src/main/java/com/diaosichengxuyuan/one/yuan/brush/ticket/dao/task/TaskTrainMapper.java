package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task;

import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskTrainDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface TaskTrainMapper extends Mapper<TaskTrainDO> {

    int updateStatusByPrimaryKey(@Param("id") Long id, @Param("status") String status);

    List<TaskTrainDO> selectAllByEndSaleTime();

}