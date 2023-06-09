package com.huasheng.dingding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@Slf4j
public class DingdingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DingdingApplication.class, args);
        log.info("========================启动完毕========================");
    }

}
