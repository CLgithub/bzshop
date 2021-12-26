package com.cl.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关服务
 * @Author l
 * @Date 2021/12/26 15:17
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy            // 开启网关代理功能
public class CommonZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonZuulApplication.class, args);
    }
}
