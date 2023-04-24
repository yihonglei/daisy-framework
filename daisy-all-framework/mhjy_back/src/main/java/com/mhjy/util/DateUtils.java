package com.mhjy.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    public static final String TIME_STAMP = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String TIME_DATE = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy年MM月";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_YEAR = "yyyy年-MM月-dd日";
    public static final String YYYYMMDDHH_YEAR = "yyyy年MM月dd日 HH:mm";
    public static final String HH_MM = "HH:mm";
    public final static String MM_DD = "MM月dd日";
    public final static String MM_DD_HH_MM = "MM月dd日 HH:mm";
    public final static String MMDD = "MMdd";
    public final static String POINT_FORMAT_DATE = "yyyy.MM.dd";
    /**年月日*/
    public final static String YYYYMMDD = "yyyyMMdd";
    private final static String[] weeks = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};

    private final static long DAY = 24 * 60 * 60 * 1000;
    private final static long HOUR = 60 * 60 * 1000;
    private final static long MINUTE = 60 * 1000;
    private final static String DIFF_TIME_DAY_STR = "%s天%s小时%s分钟";
    private final static String DIFF_TIME_HOUR_STR = "%s小时%s分钟";
    private final static String DIFF_TIME_MINTUE_STR = "%s分钟";
    public static final String HH_MM_ss = "HH:mm:ss";

    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern(TIME_DATE);
    public static final SimpleDateFormat TIME_SIMPLE_FORMAT = new SimpleDateFormat(TIME_DATE);

    /**
     * 将时间转换为时间戳
     * @param s
     * @return
     * @throws Exception
     */
    public static String dateToStamp(String s) throws Exception {
        String res;//设置时间格式，将该时间格式的时间转换为时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long time = date.getTime();
        res = String.valueOf(time);
        return res;
    }

    /**
     * 将时间转换为时间戳
     * @param s
     * @return
     * @throws Exception
     */
    public static Long dateToStampL(String s) throws Exception {
        String res;//设置时间格式，将该时间格式的时间转换为时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long time = date.getTime();
        return time;
    }

    /**
     * 将时间戳转换为时间
     * @param s
     * @return
     * @throws Exception
     */
    public static String stampToTime(String s) throws Exception{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s); //将时间戳转换为时间
        Date date = new Date(lt);//将时间调整为yyyy-MM-dd HH:mm:ss时间样式
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将时间戳转换为日期
     * @param s
     * @return
     * @throws Exception
     */
    public static Date stampToDate(String s) {
        long lt = new Long(s); //将时间戳转换为时间
        lt = lt * 1000;
        Date date = new Date(lt);//将时间调整为yyyy-MM-dd HH:mm:ss时间样式
        return date;
    }

    /**
     * 将时间格式化成分
     * @param timeStamp
     * @return
     */
    public static long formatMinute(long timeStamp) {
        DateTime dateTime = new DateTime(timeStamp);
        dateTime = dateTime.withMillisOfSecond(0).withSecondOfMinute(0);
        return dateTime.getMillis();
    }

    /**
     * 将时间格式化成分
     * @param timeStamp
     * @return
     */
    public static Date formatMinute(Date timeStamp) {
        DateTime dateTime = new DateTime(timeStamp);
        dateTime = dateTime.withMillisOfSecond(0).withSecondOfMinute(0);
        return dateTime.toDate();
    }

    /**
     * 格式化日期
     * @param timeStamp
     * @param pattern
     * @return
     */
    public static String formatString(long timeStamp, String pattern) {
        return new DateTime(timeStamp).toString(pattern);
    }

    /**
     * 格式化日期
     * @param timeStamp
     * @param pattern
     * @return
     */
    public static String formatString(Date timeStamp, String pattern) {
        return new DateTime(timeStamp).toString(pattern);
    }
    /**
     * 加分钟数
     * @param timeStamp
     * @param minutes
     * @return
     */
    public static Date plusMinutes(long timeStamp, int minutes) {
        return new DateTime(timeStamp).plusMinutes(minutes).toDate();
    }

    /**
     * 加分钟数
     * @param timeStamp
     * @param minutes
     * @return
     */
    public static Date plusMinutes(Date timeStamp, int minutes) {
        return new DateTime(timeStamp).plusMinutes(minutes).toDate();
    }

    /**
     * 加小时
     * @param timeStamp
     * @param hours
     * @return
     */
    public static Date plusHours(Date timeStamp, int hours) {
        return new DateTime(timeStamp).plusHours(hours).toDate();
    }

    /**
     * 减小时
     * @param timeStamp
     * @param hours
     * @return
     */
    public static Date minHours(Date timeStamp, int hours) {
        return new DateTime(timeStamp).minusHours(hours).toDate();
    }

    /**
     * 减分钟数
     * @param timeStamp
     * @param minutes
     * @return
     */
    public static Date minusMinutes(Date timeStamp, int minutes) {
        return new DateTime(timeStamp).minusMinutes(minutes).toDate();
    }

    /**
     * 减秒数
     * @param timeStamp
     * @param seconds
     * @return
     */
    public static Date minusSeconds(Date timeStamp, int seconds) {
        return new DateTime(timeStamp).minusSeconds(seconds).toDate();
    }

    /**
     * 解析日期
     * @param time
     * @return
     */
    public static Date parseDate(String time) {
        return formatter.parseDateTime(time).toDate();
    }

    /**
     * 解析日期
     * @param time
     * @return
     */
    public static Date parseDate(String time, String pattern) {
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        return format.parseDateTime(time).toDate();
    }

    /**
     * 获取星期几
     */
    public final static String getWeek(Date date) {
        if (date == null) {
            return null;
        }
        int dayOfWeek = new DateTime(date.getTime()).getDayOfWeek();
        return weeks[dayOfWeek - 1];
    }


    /**
     * 比较时间。传入的时间是否大于当前时间
     */
    public final static boolean compareDate(String date) {
        if (date == null) {
            return false;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.isAfterNow();
    }

    /**
     * 获取时间戳 保留到天
     *
     * @param timeStamp
     * @return
     */
    public static long getDayTimeStamp(long timeStamp) {
        DateTime dateTime = new DateTime(timeStamp);
        return dateTime.withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0).withHourOfDay(0).getMillis();
    }

    public static  Date plusDays(Date date,int days){
        return new DateTime(date).plusDays(days).toDate();
    }

    public static  Date minusDays(Date date,int days){
        return new DateTime(date).minusDays(days).toDate();
    }

    public static  Date plusMonths(Date date,int months){
        return new DateTime(date).plusMonths(months).toDate();
    }

    public static  Date minusMonths(Date date,int months){
        return new DateTime(date).minusMonths(months).toDate();
    }

    /**
     * 计算时间差，得出指定的格式。afterDate可为空，如果为空则按照当前时间计算时间差
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public static String getDistanceTime(Date beforeDate, Date afterDate) {
        if (beforeDate == null) {
            return null;
        }
        if (afterDate == null) {
            afterDate = new Date();
        }
        long diff = afterDate.getTime() - beforeDate.getTime();
        if (diff < 0) {
            return null;
        }
        long day, hour, minute;
        day = diff / DAY;
        hour = (diff - day * DAY) / HOUR;
        minute = (diff - day * DAY - hour * HOUR) / MINUTE;
        if (day == 0) {
            if (hour == 0) {
                return String.format(DIFF_TIME_MINTUE_STR, minute);
            }
            return String.format(DIFF_TIME_HOUR_STR, hour, minute);
        }
        return String.format(DIFF_TIME_DAY_STR, day, hour, minute);
    }

    /**
     * 计算时间差，多少分钟
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public static Long getDistanceMinute(Date beforeDate, Date afterDate) {
        if (beforeDate == null) {
            return null;
        }
        if (afterDate == null) {
            afterDate = new Date();
        }
        long diff = afterDate.getTime() - beforeDate.getTime();
        if (diff < 0) {
            return null;
        }
        long minute;
        minute = diff / MINUTE;
        return minute;
    }

    /**
     * 获取星期几数字
     */
    public final static Integer getWeekNumber(Date date) {
        if (date == null) {
            return null;
        }
        return new DateTime(date.getTime()).getDayOfWeek();
    }

    /**
     * 某一天的开始时间
     * @param date
     * @return
     */
    public static Long startTime(Long date) {
        Long startTime = date - (date + 8 * 3600) % 86400;
        return startTime;
    }

    /**
     * 某一天的结束时间
     * @param date
     * @return
     */
    public static Long endTime(Long date){
        return startTime(date)+86400;
    }

    public static Boolean compareSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int year2 = cal2.get(Calendar.YEAR);
        int month2 = cal2.get(Calendar.MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);

        return year1==year2&&month1==month2&&day1==day2;
    }

    /**
     * Date类型的对象转换为 yyyy-MM-dd HH:mm:ss 类型的字符串
     * @param date
     * @return
     */
    public static String getTimeString(Date date) {
        return date == null ? "" : TIME_SIMPLE_FORMAT.format(date);
    }
}
