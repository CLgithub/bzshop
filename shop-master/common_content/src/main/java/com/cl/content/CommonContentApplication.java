package com.cl.content;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * @Author l
 * @Date 2021/3/16 21:54
 */
@SpringBootApplication  // springboot
@EnableDiscoveryClient  // eureka 客户端服务自动发现
@EnableDistributedTransaction   // tx-lcn
@MapperScan("com.cl.mapper")    // mybatis 扫描
@EnableCaching                  // redis 缓存
public class CommonContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonContentApplication.class,args);
    }
}
