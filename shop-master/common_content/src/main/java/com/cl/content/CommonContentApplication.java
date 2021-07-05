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
@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
@MapperScan("com.cl.mapper")
@EnableCaching
public class CommonContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonContentApplication.class,args);
    }
}
