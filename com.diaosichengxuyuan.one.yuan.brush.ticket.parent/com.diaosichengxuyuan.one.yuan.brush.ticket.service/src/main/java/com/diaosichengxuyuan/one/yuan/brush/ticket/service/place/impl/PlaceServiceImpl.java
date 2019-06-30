package com.diaosichengxuyuan.one.yuan.brush.ticket.service.place.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.place.PlaceDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.place.PlaceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhaipeng
 * @date 2019/6/30
 */
@Service("placeService")
public class PlaceServiceImpl implements PlaceService {

    @Override
    public PlaceDTO queryPlaces() {
        List<String> hotPlaces = new ArrayList<>();
        hotPlaces.add("北京");
        hotPlaces.add("上海");
        hotPlaces.add("广州");
        hotPlaces.add("深圳");
        hotPlaces.add("天津");
        hotPlaces.add("哈尔滨");
        hotPlaces.add("长春");
        hotPlaces.add("沈阳");
        hotPlaces.add("大连");
        hotPlaces.add("石家庄");
        hotPlaces.add("济南");
        hotPlaces.add("青岛");
        hotPlaces.add("洛阳");
        hotPlaces.add("平顶山");
        hotPlaces.add("武汉");
        hotPlaces.add("长沙");
        hotPlaces.add("成都");
        hotPlaces.add("重庆");
        hotPlaces.add("南宁");
        hotPlaces.add("昆明");
        hotPlaces.add("贵阳");
        hotPlaces.add("南昌");
        hotPlaces.add("福州");
        hotPlaces.add("厦门");
        hotPlaces.add("杭州");
        hotPlaces.add("宁波");
        hotPlaces.add("合肥");
        hotPlaces.add("南京");
        hotPlaces.add("苏州");
        hotPlaces.add("无锡");
        hotPlaces.add("拉萨");
        hotPlaces.add("乌鲁木齐");
        hotPlaces.add("呼和浩特");
        hotPlaces.add("银川");
        hotPlaces.add("兰州");
        hotPlaces.add("西宁");
        hotPlaces.add("西安");
        hotPlaces.add("太原");
        List<String> places1 = new ArrayList<>();
        places1.add("阿尔山");
        places1.add("蚌埠");
        places1.add("长春");
        places1.add("东莞");
        places1.add("鄂尔多斯");
        List<String> places2 = new ArrayList<>();
        places2.add("福州");
        places2.add("杭州东");
        places2.add("合肥");
        places2.add("济南西");
        places2.add("广州东");
        List<String> places3 = new ArrayList<>();
        places3.add("昆明");
        places3.add("兰州东");
        places3.add("牡丹江");
        places3.add("漠河");
        places3.add("拉萨");
        List<String> places4 = new ArrayList<>();
        places4.add("平顶山");
        places4.add("上海虹桥");
        places4.add("石家庄北");
        places4.add("泉州东");
        places4.add("青岛");
        List<String> places5 = new ArrayList<>();
        places5.add("武汉");
        places5.add("淄博");
        places5.add("威海");
        places5.add("西安");
        places5.add("银川");
        return PlaceDTO.builder().hotPlaces(hotPlaces).places1(places1).places2(places2).places3(places3).places4(
            places4).places5(places5).build();
    }

}
