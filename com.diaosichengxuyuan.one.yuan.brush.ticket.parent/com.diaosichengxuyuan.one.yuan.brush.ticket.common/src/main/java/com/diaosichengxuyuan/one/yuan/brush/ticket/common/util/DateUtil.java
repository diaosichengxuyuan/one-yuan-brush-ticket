package com.diaosichengxuyuan.one.yuan.brush.ticket.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author liuhaipeng
 * @date 2019/6/18
 */
public class DateUtil {

    /**
     * 获取今年的最后一秒的时刻
     *
     * @param date 日期
     * @return 结果
     */
    public static Date getLastTimeOfTheYear(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.MONTH, 11);
        instance.set(Calendar.DAY_OF_MONTH, 31);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        return instance.getTime();
    }

    /**
     * 格式化日期
     *
     * @param date   日期
     * @param format 格式
     * @return 结果
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 在输入日期上增加n天
     *
     * @param date 日期
     * @param days 需要增加的天数
     * @return 结果
     */
    public static Date addDays(Date date, int days) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_MONTH, days);
        return instance.getTime();
    }

}
