package com.cl.content;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author l
 * @Date 2021/3/16 22:03
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //启用openFeign
@EnableDistributedTransaction   // 启用分布式事务管理器 tx-lcn
public class BackendContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendContentApplication.class, args);
    }
}
