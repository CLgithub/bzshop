package com.cl.cart.config;

import com.cl.cart.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 * @Author l
 * @Date 2021/12/5 16:16
 */
@SpringBootConfiguration
public class WebApplication implements WebMvcConfigurer{

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;


    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(userLoginInterceptor);
        interceptorRegistration.addPathPatterns("/frontend_cart/cart/goSettlement");
    }

//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(InterceptorRegistry registry){
//        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer();
//        InterceptorRegistration interceptorRegistration = registry.addInterceptor(userLoginInterceptor);
//        interceptorRegistration.addPathPatterns("/frontend_cart/cart/goSettlement");
//        webMvcConfigurer.addInterceptors(registry);
//        return webMvcConfigurer;
//    }



}
