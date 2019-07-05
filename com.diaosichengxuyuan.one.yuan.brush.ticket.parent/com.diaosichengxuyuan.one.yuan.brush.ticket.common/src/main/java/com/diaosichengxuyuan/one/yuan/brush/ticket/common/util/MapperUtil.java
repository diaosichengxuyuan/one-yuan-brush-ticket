package com.diaosichengxuyuan.one.yuan.brush.ticket.common.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

import java.util.List;

/**
 * 转换工具类
 *
 * @author liuhaipeng
 * @date 2018/8/26
 */
public class MapperUtil {

    private static final MapperFacade mapperFacade;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public static <S, D> D map(S sourceObject, Class<S> sourceType, Class<D> destinationType) {
        return mapperFacade.map(sourceObject, TypeFactory.valueOf(sourceType), TypeFactory.valueOf(destinationType));
    }

    public static <S, D> List<D> mapAsList(Iterable<S> source, Class<S> sourceType, Class<D> destinationType) {
        return mapperFacade.mapAsList(source, TypeFactory.valueOf(sourceType), TypeFactory.valueOf(destinationType));
    }

}
