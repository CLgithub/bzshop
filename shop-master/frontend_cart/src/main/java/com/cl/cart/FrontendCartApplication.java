package com.cl.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 购物车服务
 * @Author l
 * @Date 2021/8/11 14:54
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FrontendCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendCartApplication.class, args);
    }
}
