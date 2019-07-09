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
        hotPlaces.add("牡丹江");
        hotPlaces.add("大庆");
        hotPlaces.add("齐齐哈尔");
        hotPlaces.add("黑河");
        hotPlaces.add("长春");
        hotPlaces.add("沈阳");
        hotPlaces.add("葫芦岛");
        hotPlaces.add("抚顺");
        hotPlaces.add("大连");
        hotPlaces.add("石家庄");
        hotPlaces.add("保定");
        hotPlaces.add("唐山");
        hotPlaces.add("邯郸");
        hotPlaces.add("衡水");
        hotPlaces.add("承德");
        hotPlaces.add("秦皇岛");
        hotPlaces.add("济南");
        hotPlaces.add("青岛");
        hotPlaces.add("淄博");
        hotPlaces.add("烟台");
        hotPlaces.add("日照");
        hotPlaces.add("德州");
        hotPlaces.add("郑州");
        hotPlaces.add("洛阳");
        hotPlaces.add("平顶山");
        hotPlaces.add("商丘");
        hotPlaces.add("武汉");
        hotPlaces.add("长沙");
        hotPlaces.add("岳阳");
        hotPlaces.add("成都");
        hotPlaces.add("都江堰");
        hotPlaces.add("雅安");
        hotPlaces.add("重庆");
        hotPlaces.add("南宁");
        hotPlaces.add("昆明");
        hotPlaces.add("大理");
        hotPlaces.add("贵阳");
        hotPlaces.add("遵义");
        hotPlaces.add("六盘水");
        hotPlaces.add("南昌");
        hotPlaces.add("福州");
        hotPlaces.add("厦门");
        hotPlaces.add("武夷山");
        hotPlaces.add("杭州");
        hotPlaces.add("宁波");
        hotPlaces.add("温州");
        hotPlaces.add("义乌");
        hotPlaces.add("临安");
        hotPlaces.add("合肥");
        hotPlaces.add("芜湖");
        hotPlaces.add("马鞍山");
        hotPlaces.add("黄山");
        hotPlaces.add("蚌埠");
        hotPlaces.add("宿州");
        hotPlaces.add("阜阳");
        hotPlaces.add("南京");
        hotPlaces.add("苏州");
        hotPlaces.add("无锡");
        hotPlaces.add("扬州");
        hotPlaces.add("连云港");
        hotPlaces.add("徐州");
        hotPlaces.add("宿迁");
        hotPlaces.add("张家港");
        hotPlaces.add("拉萨");
        hotPlaces.add("乌鲁木齐");
        hotPlaces.add("呼和浩特");
        hotPlaces.add("银川");
        hotPlaces.add("兰州");
        hotPlaces.add("敦煌");
        hotPlaces.add("西宁");
        hotPlaces.add("宝鸡");
        hotPlaces.add("咸阳");
        hotPlaces.add("太原");
        hotPlaces.add("大同");
        hotPlaces.add("海口");
        hotPlaces.add("三亚");
        hotPlaces.add("武汉");
        hotPlaces.add("宜昌");
        hotPlaces.add("黄冈");
        hotPlaces.add("长春");
        hotPlaces.add("通化");
        hotPlaces.add("珠海");
        hotPlaces.add("汕头");
        hotPlaces.add("佛山");
        hotPlaces.add("东莞");
        hotPlaces.add("中山");
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
