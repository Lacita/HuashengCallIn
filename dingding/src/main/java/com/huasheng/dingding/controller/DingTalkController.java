package com.huasheng.dingding.controller;
import cn.hutool.http.server.HttpServerRequest;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserGetbyunionidRequest;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiUserGetbyunionidResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.google.gson.Gson;
import com.huasheng.dingding.Exception.MyException;
import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.common.Result.ResultUtils;
import com.huasheng.dingding.config.TokenUtils;
import com.huasheng.dingding.domain.dto.DingTalkLoginDto;
import com.huasheng.dingding.service.DingTalkCodeLogin;
import com.taobao.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "钉钉扫码登录接口")
@ApiSupport(author = "张家杰",order = 3)
@RestController
@Slf4j
@RequestMapping("/ding-talk")
public class DingTalkController {

    public static final String APP_KEY = "dingruerscmkzl824rdo";

    public static final String APP_SECRET = "uiqWe92tTkVeQYOsQ6bIDQa-cL1Ezt_eaTFeVeZU25fdLZvvg2pLCaGJWh2KEAZt";

    public static final String METHOD_TYPE = "GET";

    @Resource
    private DingTalkCodeLogin dingTalkCodeLogin;

    @ApiOperation(value = "登录获取用户相关信息")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiImplicitParam(name = "code",value = "获取临时验证码",required = true)
    public Result<String> login(@RequestParam("code") String code) {
        try {
            DefaultDingTalkClient  client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
            OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
            req.setTmpAuthCode(code);
            OapiSnsGetuserinfoBycodeResponse response = client.execute(req,APP_KEY,APP_SECRET);
            Map<String,String> map = new HashMap<>();
            map.put("nickName",response.getUserInfo().getNick());
            map.put("openId",response.getUserInfo().getOpenid());
            String codeToken = TokenUtils.signCodeToken(map);
            return ResultUtils.SUCCESS_DATA(codeToken);
            // 通过临时授权码获取授权用户的个人信息
/*            DefaultDingTalkClient client2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
            OapiSnsGetuserinfoBycodeRequest reqBycodeRequest = new OapiSnsGetuserinfoBycodeRequest();
            // 通过扫描二维码，跳转指定的redirect_uri后，向url中追加的code临时授权码
            reqBycodeRequest.setTmpAuthCode(code);
            // 修改appid和appSecret为步骤三创建扫码登录时创建的appid和appSecret
            OapiSnsGetuserinfoBycodeResponse byCodeResponse = client2.execute(reqBycodeRequest, APP_KEY, APP_SECRET);
            // 根据unionid获取userid
            String unionid = byCodeResponse.getUserInfo().getUnionid();
            DingTalkClient clientDingTalkClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/user/getbyunionid");
            OapiUserGetbyunionidRequest reqGetbyunionidRequest = new OapiUserGetbyunionidRequest();
            reqGetbyunionidRequest.setUnionid(unionid);
            OapiUserGetbyunionidResponse oapiUserGetbyunionidResponse = clientDingTalkClient.execute(reqGetbyunionidRequest, access_token);
            if (oapiUserGetbyunionidResponse.getErrcode() == 60121L) {
                return byCodeResponse.getBody();
            }
            // 根据userId获取用户信息
            String userid = oapiUserGetbyunionidResponse.getResult().getUserid();
            DingTalkClient clientDingTalkClient2 = new DefaultDingTalkClient(
                    "https://oapi.dingtalk.com/topapi/v2/user/get");
            OapiV2UserGetRequest reqGetRequest = new OapiV2UserGetRequest();
            reqGetRequest.setUserid(userid);
            reqGetRequest.setLanguage("zh_CN");
            OapiV2UserGetResponse rspGetResponse = clientDingTalkClient2.execute(reqGetRequest, access_token);
            System.out.println(rspGetResponse.getBody());
            return rspGetResponse.getBody();*/
        } catch (ApiException e) {
            throw new MyException(null);
        }
    }

    @ApiOperation(value = "获取用户Token")
    @RequestMapping(value = "/getJwtToken",method = RequestMethod.POST)
    public Result<String> getJwtToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return ResultUtils.ERROR("用户信息获取失败");
        }
        try {
            String userTokenID = TokenUtils.getUserTokenID(token);
            return ResultUtils.SUCCESS_DATA(userTokenID);
        }catch (Exception e){
            log.error(e.toString());
            throw new MyException("用户token解析异常");
        }
    }

    public String getToken() {
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(APP_KEY);
            request.setAppsecret(APP_SECRET);
            request.setHttpMethod(METHOD_TYPE);
            OapiGettokenResponse response = client.execute(request);
            return response.getAccessToken();
        } catch (ApiException e) {
            throw new RuntimeException();
        }
    }
}
