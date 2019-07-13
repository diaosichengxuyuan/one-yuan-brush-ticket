package com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TicketTestMapper extends Mapper<TicketTestDO> {

    int updateByPrimaryKeyAndRemain(@Param("remain1") int remain1, @Param("id") Long id, @Param("remain2") int remain2);

}