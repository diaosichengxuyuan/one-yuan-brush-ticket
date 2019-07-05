package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger;

import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger.entity.PassengerDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PassengerMapper extends Mapper<PassengerDO> {

    List<PassengerDO> queryListByIds(@Param("ids") List<Long> ids);
}