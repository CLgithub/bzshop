package com.cl.zuul.config;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;


/**
 * @Author l
 * @Date 2022/2/1 13:41
 */
@SpringBootConfiguration
public class MyConfig {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    /**
     * 设置每秒产生的令牌数
     */
    private static final RateLimiter RATE_LIMIT_FILTER = RateLimiter.create(10);

    @Bean
    public ZuulFilter getRateLimitFilter(){
        return new ZuulFilter() {
            @Override
            public String filterType() {
                return FilterConstants.PRE_TYPE;
            }

            @Override
            public int filterOrder() {
                return FilterConstants.SERVLET_30_WRAPPER_FILTER_ORDER-1;
            }

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() throws ZuulException {
                if(!RATE_LIMIT_FILTER.tryAcquire()){
                    RequestContext currentContext = RequestContext.getCurrentContext();
                    currentContext.setSendZuulResponse(false);
                    currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
                    logger.warn("请求过多，限量访问！");
                }
                return null;
            }
        };
    }
}
