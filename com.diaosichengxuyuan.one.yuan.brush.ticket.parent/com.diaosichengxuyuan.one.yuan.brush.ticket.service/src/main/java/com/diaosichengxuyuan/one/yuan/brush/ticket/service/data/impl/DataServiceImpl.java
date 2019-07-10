package com.diaosichengxuyuan.one.yuan.brush.ticket.service.data.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.MapperUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.data.DataMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.data.entity.DataDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.data.DataService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.data.DataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuhaipeng
 * @date 2019/7/6
 */
@Service("dataService")
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Override
    public DataDTO queryData() {
        DataDO dataDO = dataMapper.selectByPrimaryKey(1);
        DataDTO dataDTO = MapperUtil.map(dataDO, DataDO.class, DataDTO.class);
        return dataDTO;
    }

    @Override
    public void increaseTaskNumber() {
        DataDO dataDO = dataMapper.selectByPrimaryKey(1);
        if(dataDO == null) {
            return;
        }

        dataMapper.updateByPrimaryKeySelective(DataDO.builder().id(1L).taskNum(dataDO.getTaskNum() + 1).build());
    }

    @Override
    public void increaseUserNumber() {
        DataDO dataDO = dataMapper.selectByPrimaryKey(1);
        if(dataDO == null) {
            return;
        }

        dataMapper.updateByPrimaryKeySelective(DataDO.builder().id(1L).userNum(dataDO.getUserNum() + 1).build());
    }

    @Override
    public void increaseMemberNumber() {
        DataDO dataDO = dataMapper.selectByPrimaryKey(1);
        if(dataDO == null) {
            return;
        }

        dataMapper.updateByPrimaryKeySelective(DataDO.builder().id(1L).memberNum(dataDO.getMemberNum() + 1).build());
    }

}
