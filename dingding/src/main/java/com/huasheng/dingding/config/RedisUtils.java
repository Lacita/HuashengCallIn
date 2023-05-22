package com.huasheng.dingding.config;

import cn.hutool.core.util.IdUtil;
import com.huasheng.dingding.common.Constant.RedisConstant;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.GeoOperations;
import javax.annotation.Resource;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;
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
        assert distance != null;
        geoOperations.remove(RedisConstant.GRO_KEY,userId);
        return distance.getValue();
    }


    //根据用户当前经纬度信息查询距离
    public double getABaoDistance(double longitude,double latitude,String userId){
        GeoOperations<String, Object> geoOperations = redisTemplate.opsForGeo();
        // 将这些地址数据保存到redis中
        geoOperations.add(RedisConstant.GRO_A_BAO_KEY,new Point(RedisConstant.LOCAL_A_BAO_LONGITUDE,RedisConstant.LOCAL_A_BAO_LATITUDE),"aBao");
        geoOperations.add(RedisConstant.GRO_A_BAO_KEY,new Point(longitude,latitude),userId);
        Distance distance = geoOperations.distance(RedisConstant.GRO_A_BAO_KEY, "aBao", userId,Metrics.KILOMETERS);
        assert distance != null;
        geoOperations.remove(RedisConstant.GRO_A_BAO_KEY,userId);
        return distance.getValue();
    }

    // 根据用户打卡位置获取圆心
    public boolean getLocationGeoRadius(double longitude,double latitude,String userId){
        GeoOperations<String, Object> geoOperations = redisTemplate.opsForGeo();
//        GeoLocation<String> current = new GeoLocation<>(userId, new Point(longitude, latitude));
//        GeoResults<GeoLocation<Object>> radius = geoOperations.
//                radius(RedisConstant.GRO_KEY, new Circle(current.getPoint(), new Distance(0.2,Metrics.KILOMETERS)));
//        long count = radius.getContent().stream().count();
//        return count != 0;
//        System.out.println(count);
//        return !radius.getContent().isEmpty();
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().
                includeDistance().includeCoordinates().sortAscending().limit(5);
        Circle circle = new Circle(new Point(longitude, latitude), new Distance(0.2, Metrics.KILOMETERS));
        GeoResults<GeoLocation<Object>> radius = geoOperations.radius(RedisConstant.GRO_KEY, circle, args);
        long count = radius.getContent().stream().count();
        return count != 0;
//        System.out.println(radius);
//        return false;
    }


}
