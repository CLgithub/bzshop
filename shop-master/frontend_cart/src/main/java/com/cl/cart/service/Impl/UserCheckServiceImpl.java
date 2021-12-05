package com.cl.cart.service.Impl;

import com.cl.cart.service.UserCheckService;
import com.cl.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author l
 * @Date 2021/12/5 16:07
 */
@Service
public class UserCheckServiceImpl implements UserCheckService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${frontend_user_redis_key}")
    private String frontend_user_redis_key;

    @Override
    public TbUser checkUserToken(String token) {
        Object o = redisTemplate.opsForHash().get(frontend_user_redis_key, token);
        return (TbUser) o;
    }
}
