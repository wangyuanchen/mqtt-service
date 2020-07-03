package com.wyc.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: wangyuanchen
 * @date: 2020-5-11 08:36
 * @description:
 */
public class TimeUtil {
    public static final String PATTERN_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String PATTERN_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 获取半小时后时间
     *
     * @return 半小时后时间
     */
    public static Date halfAnHourLater() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30);
        return calendar.getTime();
    }

    /**
     * 获取当前时间几天后的时间
     * @return 当前时间几天前后的时间
     */
    public static Date xDaysBefore(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 获取传入时间几天后的时间
     * @param date 传入的时间
     * @param days 几天后
     * @return 传入时间几天后的时间
     */
    public static Date xDaysBeforeToString(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 按照指定的格式，将当前时间格式化后返回
     *
     * @param pattern 指定的时间格式
     * @return 格式化后的当前时间
     */
    public static String formatNow(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Date类型转String
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zone).toLocalDateTime();
        return formatter.format(localDateTime);
    }

    /**
     * String类型转Date 自定义格式
     * @param date
     * @return String
     */
    public static Date dateToString(String date, String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            pattern = PATTERN_YYYYMMDD_HHMMSS;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date resultDate = Date.from(zdt.toInstant());
        return resultDate;
    }

}
