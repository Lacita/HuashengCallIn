package com.huasheng.dingding.service.Imp;

import com.huasheng.dingding.Exception.MyException;
import com.huasheng.dingding.common.Result.Result;
import com.huasheng.dingding.common.Result.ResultUtils;
import com.huasheng.dingding.config.TokenUtils;
import com.huasheng.dingding.service.DingTalkCodeLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DingTalkCodeLoginService implements DingTalkCodeLogin {
    @Override
    public Result<String> getJwtToken(String dingId, String nick, String openid, String unionid) {
//        try{
//            String signCodeToken = TokenUtils.signCodeToken(dingId, nick, openid, unionid);
//            return ResultUtils.SUCCESS_DATA(signCodeToken);
//        }
//        catch (Exception e){
//            throw new MyException("token返回异常");
//        }
        return null;

    }
}
