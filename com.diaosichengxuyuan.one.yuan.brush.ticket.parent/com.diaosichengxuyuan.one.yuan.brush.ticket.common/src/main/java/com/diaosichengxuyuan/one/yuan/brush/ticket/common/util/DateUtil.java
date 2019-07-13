package com.diaosichengxuyuan.one.yuan.brush.ticket.common.util;

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
        //mysql数据库可能会因为毫秒的精度导致进位
        instance.set(Calendar.MILLISECOND, 0);
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
     * 在输入日期上增加时间
     *
     * @param date 日期
     * @param days 需要增加的天数
     * @return 结果
     */
    public static Date addDays(Date date, int days, int hours, int minutes) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_MONTH, days);
        instance.add(Calendar.HOUR_OF_DAY, hours);
        instance.add(Calendar.MINUTE, minutes);
        return instance.getTime();
    }

    public static String toWeek(int week) {
        if(week == 1) {
            return "周一";
        }
        if(week == 2) {
            return "周二";
        }
        if(week == 3) {
            return "周三";
        }
        if(week == 4) {
            return "周四";
        }
        if(week == 5) {
            return "周五";
        }
        if(week == 6) {
            return "周六";
        }
        if(week == 7) {
            return "周日";
        }

        return null;
    }

}
