package com.huasheng.dingding.config;

import com.alibaba.fastjson.JSON;
import com.huasheng.dingding.common.Result.ResultUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        String token = request.getHeader("token");
        if (token == null || "".equals(token)) {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResultUtils.NO_ACCESS_DATA("NO LOGIN!")));
            writer.flush();
            writer.close();
            return false;
        }
        if (!TokenUtils.verify(token)) {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResultUtils.NO_ACCESS_DATA("TIME OUT")));
            writer.flush();
            writer.close();
            return false;
        }
        if (TokenUtils.isValidToken(token)){
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
