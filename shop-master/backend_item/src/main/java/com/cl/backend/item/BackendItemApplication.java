package com.cl.backend.item;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import sun.management.Agent;

/**
 * backenditem服务
 * @Author l
 * @Date 2020/9/21 20:19
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //启用openFeign
@EnableDistributedTransaction   // 启用分布式事务管理器 tx-lcn
public class BackendItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendItemApplication.class, args);
    }
}
