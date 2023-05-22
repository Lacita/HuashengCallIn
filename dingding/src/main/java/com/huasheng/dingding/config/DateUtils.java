package com.huasheng.dingding.config;

import com.huasheng.dingding.Exception.MyException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static long  nd = 1000 * 24 * 60 * 60;
    public static long  nh = 1000 * 60 * 60;
    public static long  nm = 1000 * 60;
    public static long  ns = 1000;

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

    /***
     *
     * 判断是否迟到
     * @return
     */
    public static boolean isLate(){
        LocalTime normalTime = LocalTime.of(8, 0);
        return !LocalTime.now().isBefore(normalTime);
    }

    /***
     *
     * 判断是否早退
     * @return
     */
    public static boolean isEarly(){
        LocalTime normalTime = LocalTime.of(17, 30);
        return LocalTime.now().isBefore(normalTime);
    }

    /**
     * 计算迟到时间
     * @return
     */
    public static String calcLateTime(){

        try {
            // 实例化 DateTimeFormatter 对象，设定时间格式
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            // 获取当前系统时间
            LocalTime currentTime = LocalTime.now();
            String currentTimeStr = currentTime.format(dtf);
            Date now = simpleDateFormat.parse(currentTimeStr);
            // 设定已知时间
            String inputTimeStr = "08:00:00";
            Date callInTime = simpleDateFormat.parse(inputTimeStr);
            long time = now.getTime() - callInTime.getTime();
            long hour = time % nd / nh;
            long minutes = time % nd % nh / nm;
            long second = time % nd % nh % nm / ns;
            return hour+"时"+minutes+"分"+second+"秒";
//            LocalTime inputTime = LocalTime.parse(inputTimeStr, dtf);
//            // 计算时间差
//            Duration duration = Duration.between(inputTime, currentTime);
//            long timeDifference = duration.getSeconds();
//            System.out.println("时间差为：" + timeDifference + " 秒");
        } catch (Exception e) {
            throw new MyException("时间解析异常");
        }
    }

    /**
     * 计算早退时间
     * @return
     */
    public static String calcEarlyTime(){

        try {
            // 实例化 DateTimeFormatter 对象，设定时间格式
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            // 获取当前系统时间
            LocalTime currentTime = LocalTime.now();
            String currentTimeStr = currentTime.format(dtf);
            Date now = simpleDateFormat.parse(currentTimeStr);
            // 设定已知时间
            String inputTimeStr = "17:30:00";
            Date offTime = simpleDateFormat.parse(inputTimeStr);
            long time = offTime.getTime() - now.getTime();
            long hour = time % nd / nh;
            long minutes = time % nd % nh / nm;
            long second = time % nd % nh % nm / ns;
            return hour+"时"+minutes+"分"+second+"秒";
//            LocalTime inputTime = LocalTime.parse(inputTimeStr, dtf);
//            // 计算时间差
//            Duration duration = Duration.between(inputTime, currentTime);
//            long timeDifference = duration.getSeconds();
//            System.out.println("时间差为：" + timeDifference + " 秒");
        } catch (Exception e) {
            throw new MyException("时间解析异常");
        }
    }


    public static String getDiffTime(String beginTime,String endTime){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = simpleDateFormat.parse(beginTime);
            Date end = simpleDateFormat.parse(endTime);
            long time = end.getTime() - start.getTime();
            long hour = time % nd / nh;
            long minutes = time % nd % nh / nm;
            long second = time % nd % nh % nm / ns;
            return hour+"时"+minutes+"分"+second+"秒";
        }
        catch (ParseException pa){
            throw new MyException(pa.toString());
        }
    }
}
