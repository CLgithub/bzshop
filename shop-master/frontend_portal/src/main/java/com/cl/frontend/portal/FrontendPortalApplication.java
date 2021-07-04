package com.cl.frontend.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 用于查询首页广告位内容
 * @Author l
 * @Date 2021/6/28 15:20
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FrontendPortalApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FrontendPortalApplication.class, args);
    }
}
