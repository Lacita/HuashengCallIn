package com.huasheng.dingding.service;

import com.huasheng.dingding.common.Result.Result;

public interface DingTalkCodeLogin {

     Result<String> getJwtToken(String dingId, String nick,String openid,String unionid);

}
