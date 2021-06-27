package com.zhu.sm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class BeginApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeginApplication.class);
    }
}

