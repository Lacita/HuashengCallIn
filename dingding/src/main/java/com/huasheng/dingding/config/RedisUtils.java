package com.huasheng.dingding.config;

import cn.hutool.core.util.IdUtil;
import com.huasheng.dingding.common.Constant.RedisConstant;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    //根据用户当前经纬度信息查询距离
    public double getDistance(double longitude,double latitude,String userId){
        GeoOperations<String, Object> geoOperations = redisTemplate.opsForGeo();
        // 将这些地址数据保存到redis中
        geoOperations.add(RedisConstant.GRO_KEY,new Point(RedisConstant.LOCAL_LONGITUDE,RedisConstant.LOCAL_LATITUDE),"local");
        geoOperations.add(RedisConstant.GRO_KEY,new Point(longitude,latitude),userId);
        Distance distance = geoOperations.distance(RedisConstant.GRO_KEY, "local", userId,Metrics.KILOMETERS);
        System.out.println(distance);
        assert distance != null;
        geoOperations.remove(RedisConstant.GRO_KEY,userId);
        return distance.getValue();
    }


}
