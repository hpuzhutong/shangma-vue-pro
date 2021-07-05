package com.zhu.sm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 17:19
 * @className: BeginApplication
 * @description:
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.zhu.sm.mapper"})
@EnableScheduling  //开启任务调度  使用threadPoolTaskScheduler
@EnableAsync  //开启异步操作
public class BeginApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeginApplication.class);
    }
}

