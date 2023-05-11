package com.huasheng.dingding.common.Constant;

public class RedisConstant {

    /***
     * redis的Key
     */
    public static final String REDIS_KEY="CLOCK_IN:";

    /***
     * redis的Key
     */
    public static final String GRO_KEY="GEO_DISTANCE:";

    /***
     * 当前公司经度
     */
    public static double LOCAL_LONGITUDE = 113.787033;

    /***
     * 当前公司纬度
     */
    public static double LOCAL_LATITUDE = 22.922186;

    /***
     * 指定打卡范围
     */
    public static double LIMIT_DISTANCE = 0.2 ;

    /***
     * 外勤打卡类型
     */
    public static String FIELD_TYPE = "4" ;

}
