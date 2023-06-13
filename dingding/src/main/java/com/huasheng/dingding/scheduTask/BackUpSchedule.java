package com.huasheng.dingding.scheduTask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@Slf4j
public class BackUpSchedule {

    @Async
    @Scheduled(cron = "0 0 4 * * ?")
    public void execute(){
        try {
            log.info("=======================开始备份数据库=========================");
            // G:\HS-DDDK\HS-DDDK
            Runtime.getRuntime().exec("cmd /c G:/HS-DDDK2222222222/HS-DDDK222222222222/backUp.bat"); // 执行备份批处理文件
            log.info("=======================数据库备份成功=========================");
        } catch (IOException e) {
            log.error("数据库备份失败",e);
        }
    }
}
