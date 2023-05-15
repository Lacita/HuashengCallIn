package com.huasheng.dingding.common.CallInUtils;

import com.huasheng.dingding.common.Result.Result;
import org.springframework.stereotype.Service;

public interface CallInStrategy {

    public Result<String> callInStrategy(String userName, String userId, String type,
                                         String location, String project, String note);

}
