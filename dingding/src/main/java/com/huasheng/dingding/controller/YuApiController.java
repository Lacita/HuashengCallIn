package com.huasheng.dingding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huasheng.dingding.Exception.MyException;
import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.common.Result.ResultUtils;
import com.huasheng.dingding.config.RedisUtils;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "智能报表分析")
@ApiSupport(author = "张家杰",order = 2)
//@RestController
//@RequestMapping("/api")
public class YuApiController {

    public static final String accessKey = "gzztm82ebbf14nyro8cqoe9wg5p1pgno";

    public static final String secretKey = "cnmtmj0yyoludthxq1ds5k3eydnriceb";

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public Result<Object> getApiResponse(String body){
        YuCongMingClient client = new YuCongMingClient(accessKey, secretKey);
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(1661659870119677953L);
        devChatRequest.setMessage(body);
        try {
            BaseResponse<DevChatResponse> response = client.doChat(devChatRequest);
            return ResultUtils.SUCCESS_DATA(response.getData());
        }
        catch (Exception e){
            throw new MyException(e.toString());
        }
    }

}
