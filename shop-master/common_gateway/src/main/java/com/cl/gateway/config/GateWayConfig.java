package com.cl.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author l
 * @Date 2022/3/10 21:31
 */
@Configuration
public class GateWayConfig {



    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        RouteLocator r2_1501=routes.route("r2_1501",new PredicateSpec("").uri("http://www.baidu.com")).build();
        RouteLocator r2_1501 = routes.route(
                "r1_cloud-backend-content",
                r -> r.path("/backend_content/**").uri("lb://cloud-backend-content")
        ).build();
        return r2_1501;
    }

}
