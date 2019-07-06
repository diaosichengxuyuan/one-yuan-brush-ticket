package com.diaosichengxuyuan.one.yuan.brush.ticket.service.data;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.data.DataDTO;

/**
 * 数据接口
 *
 * @author liuhaipeng
 * @date 2019/7/6
 */
public interface DataService {

    /**
     * 查询所有数据
     *
     * @return
     */
    DataDTO queryData();

    /**
     * 增加任务个数
     */
    void increaseTaskNumber();

    /**
     * 增加用户个数
     */
    void increaseUserNumber();

    /**
     * 增加会员个数
     */
    void increaseMemberNumber();

}
