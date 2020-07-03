package com.wyc.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     *时间格式(yyyyMMddHHmmss)
     */
    public final static String DATE_TIME_YMDHMS="yyyyMMddHHmmss";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static long day = 0L;
    private static long hour = 0L;
    private static long min = 0L;
    private static long s = 0L;

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String formatYMDSFM(Date date){
        return format(date, DATE_TIME_YMDHMS);
    }
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 获取yyyyMMddHHmmss格式时间
     *
     * @Author guoyi
     * @Date 2019/11/21 11:07
     */
    public static String formatNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_YMDHMS));
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        initTime(date);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        initTime(date);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    private static void initTime(Date date){
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        day = l / (24 * 60 * 60 * 1000);
        hour = (l / (60 * 60 * 1000) - day * 24);
        min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
    }

    /**
     * Long型时间转yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = sdf.format(date);
        return timeStr;
    }


}
