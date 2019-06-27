package com.diaosichengxuyuan.one.yuan.brush.ticket.common.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * 转换工具类
 *
 * @author liuhaipeng
 * @date 2018/8/26
 */
public class MapperUtil {

    public static MapperFacade getMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        return mapperFactory.getMapperFacade();
    }

}
