package com.diaosichengxuyuan.one.yuan.brush.ticket.common.util;

/**
 * 匿名化工具类
 *
 * @author liuhaipeng
 * @date 2019/7/7
 */
public class AnonymousUtil {

    public static String anonymize(String input) {
        int length = input.length();
        if(length <= 2) {
            return input;
        }

        int mid = length / 2;
        if(length <= 7) {
            return input.substring(0, mid) + "*" + input.substring(mid + 1, length);
        }

        return input.substring(0, mid - 1) + "***" + input.substring(mid + 2, length);
    }

}
