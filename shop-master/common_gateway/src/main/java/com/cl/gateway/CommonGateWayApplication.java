package com.cl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author l
 * @Date 2022/3/10 21:05
 */
@SpringBootApplication
@EnableEurekaClient
public class CommonGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonGateWayApplication.class, args);
    }
}
