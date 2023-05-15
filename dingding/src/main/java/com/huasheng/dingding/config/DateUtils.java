package com.huasheng.dingding.config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static boolean isOverTime() {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
//        LocalTime beginTime = LocalTime.parse("17:00:00", dtf);
//        LocalTime endTime = LocalTime.parse("08:00:00", dtf);
//        LocalTime.now().
//        return LocalTime.now().isAfter(beginTime)&& LocalTime.now().isBefore(endTime);
        LocalTime beforeTime = LocalTime.of(8, 0);
        LocalTime afterTime = LocalTime.of(18, 0);
        LocalTime now = LocalTime.now();
        return now.isAfter(beforeTime) && now.isBefore(afterTime);
    }
}
