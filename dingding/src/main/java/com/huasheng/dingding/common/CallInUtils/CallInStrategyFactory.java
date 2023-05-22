package com.huasheng.dingding.common.CallInUtils;

import com.huasheng.dingding.common.Constant.RedisConstant;

import java.util.HashMap;
import java.util.Map;

public class CallInStrategyFactory {

    private static final Map<String,CallInStrategy> map = new HashMap<>();

    static {
        map.put(RedisConstant.CALL_IN_TYPE,new CallInOperate());
        map.put(RedisConstant.CALL_IN_TYPE_AN,new CallInOperate());
        map.put(RedisConstant.KNOCK_OFF_TYPE,new KnockOffOperate());
        map.put(RedisConstant.KNOCK_OFF_TYPE_AN,new KnockOffOperate());
        map.put(RedisConstant.FIELD_CALL_IN_TYPE,new FieldTypeOperate());
        map.put(RedisConstant.FIELD_CALL_IN_TYPE_AN,new FieldTypeOperate());
        map.put(RedisConstant.FIELD_KNOCK_TYPE,new FieldKnockTypeOperate());
        map.put(RedisConstant.FIELD_KNOCK_TYPE_AN,new FieldKnockTypeOperate());
        map.put(RedisConstant.OVER_TIME_TYPE,new OverTimeOperate());
        map.put(RedisConstant.OVER_TIME_TYPE_AN,new OverTimeOperate());
    }

    public static CallInStrategy getStrategy(String type) {
        return map.get(type);
    }
}
