package com.cl.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 分布式配置中心服务端
 * @Author l
 * @Date 2022/2/1 16:22
 *
 springCloud 分布式配置中心服务端配置步骤：
    1、添加坐标
        分布式配置中心服务端
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        bus 总线 AMQP
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
    2、添加配置
        1）配置git服务端地址
        2）配置rabbitmq
        3）配置开启自动刷新
        4）bus-refresh

springCloud 分布式配置中心客户端端配置步骤：
    1、添加坐标
        分布式配置中心客户端
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        bus 总线 AMQP
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
    2、配置消息队列rabbitmq
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer     // 分布式配置中心服务端
public class CommonConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonConfigApplication.class, args);
    }
}
