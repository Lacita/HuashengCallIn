package com.huasheng.dingding.config;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiV2DepartmentGetRequest;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.request.OapiV2UserGetuserinfoRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiV2DepartmentGetResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.dingtalk.api.response.OapiV2UserGetuserinfoResponse;
import com.huasheng.dingding.Exception.MyException;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class DingTalkUtils {

    /***
     * 填写小程序信息
     */
    public static final String APP_KEY = "";

    public static final String APP_SECRET = "";

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    //获取访问access_token
    public String getAccessToken(){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(APP_KEY);
        request.setAppsecret(APP_SECRET);
        request.setHttpMethod("GET");
        try {
            OapiGettokenResponse response = client.execute(request);
            return response.getAccessToken();
        }
        catch (ApiException api){
            log.error("获取ACCESS_TOKEN失败，故障代码为:{}",api.getErrMsg());
            throw new MyException(api.getMessage());
        }
    }

    // 免密获取当前用户信息
    public Map<String,Object> getUserinfo(String accessToken, String userCode){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getuserinfo");
        OapiV2UserGetuserinfoRequest req = new OapiV2UserGetuserinfoRequest();
        req.setCode(userCode);
        try {
            OapiV2UserGetuserinfoResponse rsp = client.execute(req,accessToken);
            String name = rsp.getResult().getName();
            String userid = rsp.getResult().getUserid();
            Map<String,Object> map = new HashMap<>(2);
            map.put("name",name);
            map.put("userid",userid);
            return map;
        }
        catch (Exception e){
            log.error("免密获取用户信息失败，当前异常为:{}",e.getMessage());
            throw new MyException(e.getMessage());
        }
    }

    // 通过userid获取部门信息以及员工岗位
    public Map<String,Object> getUserDetails(String accessToken,String userID){
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
            OapiV2UserGetRequest req = new OapiV2UserGetRequest();
            req.setUserid(userID);
            req.setLanguage("zh_CN");
            OapiV2UserGetResponse rsp = client.execute(req, accessToken);
            String title = rsp.getResult().getTitle();
            String name = rsp.getResult().getName();
            String jobNumber = rsp.getResult().getJobNumber();
            List<Long> deptIdList = rsp.getResult().getDeptIdList();
            HashMap<String, Object> map = new HashMap<>(3);
            map.put("title",title);
            map.put("name",name);
            map.put("jobNumber",jobNumber);
            map.put("deptIdList",deptIdList);
            return map;
        } catch (ApiException e) {
            log.error("通过userid获取部门信息以及员工岗位异常，异常信息为:{}",e.getMessage());
            throw new MyException(e.getMessage());
        }
    }

    // 获取部门详情
    public String getDeptDetails(String accessKey,Long deptID){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/get");
        OapiV2DepartmentGetRequest req = new OapiV2DepartmentGetRequest();
        req.setDeptId(deptID);
        req.setLanguage("zh_CN");
        try{
            OapiV2DepartmentGetResponse rsp = client.execute(req, accessKey);
            return rsp.getResult().getName();
        }
        catch (ApiException api){
            log.error("获取部门详情失败,异常信息为:{}",api.getMessage());
            throw new MyException(api.getMessage());
        }

    }

    // 判断token是否过期
    public String accessKeyExpire(){
        String key = null;
        String accessKey = (String)redisTemplate.opsForValue().get("accessKey");
        if (StringUtils.isBlank(accessKey)) {
            key = this.getAccessToken();
            redisTemplate.opsForValue().set("accessKey",key,120, TimeUnit.MINUTES);
            return key;
        }
        return accessKey;
    }

}
