package com.zhu.thyme_demo.timing_task;

import com.zhu.thyme_demo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Auther: Joanne
 * @Date: 2018/11/20 10:45
 * @Description:
 */
@Configuration
@EnableScheduling
public class ScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

//    @Scheduled(cron = "0 0/1 * * * ?") // 每10分钟执行一次
    @Scheduled(cron = "0 0/20 1,2 * * ?") //在每天凌晨1点到1:40期间和凌晨2点到2:40期间的每20分钟触发
    public void deleteFile(){
        logger.info("======{}定时删除文件夹文件======", LocalDateTime.now());
        FileUtil.deleteDirChild(new File("C:\\zwy\\outfile"));
    }
}
