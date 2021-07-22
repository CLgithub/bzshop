package com.cl.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 搜索服务
 * @Author l
 * @Date 2021/7/5 17:16
 */
@SpringBootApplication
@MapperScan("com.cl.mapper")
public class FrontendSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendSearchApplication.class, args);
    }
}
