package com.huasheng.dingding.common.Constant;

import java.util.HashMap;
import java.util.Map;

public class RedisConstant {

    /***
     * redis的Key
     */
    public static final String REDIS_KEY="CLOCK_IN:";

    /***
     * redis的Key
     */
    public static final String GRO_KEY="local";

    /***
     * redis的Key
     */
    public static final String GRO_A_BAO_KEY="GEO_DISTANCE:aBao:";

    /***
     * 当前科能工业园经度
     */
    public static double LOCAL_LONGITUDE = 113.787033;

    /***
     * 当前科能工业园纬度
     */
    public static double LOCAL_LATITUDE = 22.922186;

    /***
     * 当前阿宝工业园经度
     */
    public static double LOCAL_A_BAO_LONGITUDE = 113.806150;

    /***
     * 当前阿宝工业园纬度
     */
    public static double LOCAL_A_BAO_LATITUDE = 22.911329;

    /***
     * 指定打卡范围
     */
    public static double LIMIT_DISTANCE = 0.2 ;

    /***
     * 上班打卡
     */
    public static String CALL_IN_TYPE = "1" ;

    /***
     * 上班安卓打卡
     */
    public static String CALL_IN_TYPE_AN = "01" ;

    /***
     * 下班班打卡
     */
    public static String KNOCK_OFF_TYPE = "2" ;

    /***
     * 下班班打卡
     */
    public static String KNOCK_OFF_TYPE_AN = "02" ;

    /***
     * 外勤上班打卡类型
     */
    public static String FIELD_CALL_IN_TYPE = "3" ;

    /***
     * 外勤上班打卡类型
     */
    public static String FIELD_CALL_IN_TYPE_AN = "03" ;

    /***
     * 外勤下班打卡类型
     */
    public static String FIELD_KNOCK_TYPE = "4" ;

    /***
     * 外勤下班打卡类型
     */
    public static String FIELD_KNOCK_TYPE_AN = "04" ;

    /***
     * 加班打卡上班类型
     */
    public static String OVER_TIME_TYPE = "5" ;

    /***
     * 加班打卡上班类型
     */
    public static String OVER_TIME_TYPE_AN = "05" ;

    /***
     * 加班打卡下班类型(暂未使用)
     */
    public static String OVER_TIME_END_TYPE = "6" ;

    /***
     * 装载打卡类型具体的策略类
     */
    public static Map<String,String> TYPE = new HashMap<>();

    static {
        TYPE.put("1","callInOperate");
        TYPE.put("2","knockOffOperate");
        TYPE.put("3","fieldTypeOperate");
        TYPE.put("4","fieldKnockTypeOperate");
        TYPE.put("5","overTimeOperate");
    }


}
