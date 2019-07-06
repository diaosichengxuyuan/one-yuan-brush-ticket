package com.diaosichengxuyuan.one.yuan.brush.ticket.web.data.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.data.DataService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.data.DataDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.data.DataRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuhaipeng
 * @date 2019/7/6
 */
@RestController("dataRemote")
@RequestMapping("/data")
public class DataRemoteImpl implements DataRemote {

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/queryData",method = RequestMethod.GET)
    @Override
    public DataDTO queryData() {
        return dataService.queryData();
    }

}
