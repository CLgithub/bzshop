package com.cl.item;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 通用服务
 * @Author l
 * @Date 2020/9/21 17:24
 */
//@EnableEurekaClient 只支持eureka
@EnableDiscoveryClient //支持其他注册中心，但其实可以都不加
@SpringBootApplication
@MapperScan("com.cl.mapper")    // 扫描mapper映射文件
@EnableDistributedTransaction   // 启用分布式事务管理器 tx-lcn
public class CommonItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonItemApplication.class, args);
    }
}
