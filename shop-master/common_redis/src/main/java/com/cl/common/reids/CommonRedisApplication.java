package com.cl.common.reids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author l
 * @Date 2021/6/30 16:31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CommonRedisApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(CommonRedisApplication.class, args);

//        Object setRedisTemplate = context.getBean("setRedisTemplate");
//        System.out.println(setRedisTemplate);
//
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//
//        // 2 查看容器里的组件
//        for(String beanName:beanDefinitionNames){
//            if(beanName.contains("RedisTempl")){
//                System.out.println("name："+beanName+"，type："+context.getBean(beanName).getClass().getName()+"，bean："+context.getBean(beanName));
//
//            }
//        }
    }
}
