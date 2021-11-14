package com.cl.sso;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户注册与登录服务，实现单点登录，多应用共享登录
 * @Author l
 * @Date 2021/11/8 22:31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients     // 需要调用feign
@MapperScan("com.cl.mapper")    // 需要操作数据库，需要扫描
@EnableDistributedTransaction // 分布式事务
@EnableCaching  // 启用缓存
public class FrontendSSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendSSOApplication.class, args);
    }
}
