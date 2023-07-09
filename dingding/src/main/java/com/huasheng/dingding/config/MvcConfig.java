package com.huasheng.dingding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public static final List<String>  withList = Arrays.asList("/hs/clockIn",
            "/hs/export","/hs/login","/doc.html/**",
            "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
            "/api", "/api-docs", "/api-docs/**","/hs/getUserInfo","/v3/api-docs",
            "/api/query","/test/**","/ding-talk/**","/test/**"
            );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(withList);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders()
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT")
                .maxAge(3600*24);
    }
}
