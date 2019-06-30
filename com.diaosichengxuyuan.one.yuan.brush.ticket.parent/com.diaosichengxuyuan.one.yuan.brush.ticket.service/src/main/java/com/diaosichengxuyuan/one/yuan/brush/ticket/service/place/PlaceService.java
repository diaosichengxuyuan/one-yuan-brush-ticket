package com.diaosichengxuyuan.one.yuan.brush.ticket.service.place;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.place.PlaceDTO;

/**
 * 地点查询
 *
 * @author liuhaipeng
 * @date 2019/6/30
 */
public interface PlaceService {

    /**
     * 查询所有地点
     * @return 所有地点
     */
    PlaceDTO queryPlaces();

}
