package com.diaosichengxuyuan.one.yuan.brush.ticket.web.remote.place.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.place.PlaceDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.place.PlaceService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.remote.place.PlaceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuhaipeng
 * @date 2019/6/30
 */
@RestController("placeRemote")
@RequestMapping("/place")
public class PlaceRemoteImpl implements PlaceRemote {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/queryPlaces", method = RequestMethod.GET)
    @Override
    public PlaceDTO queryPlaces() {
        return placeService.queryPlaces();
    }

}
