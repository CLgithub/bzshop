package com.cl.common.reids.service.impl;

import com.cl.common.reids.service.ItemCategoryService;
import com.cl.gzshop.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author l
 * @Date 2021/7/1 11:27
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name= "setRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${frontend_catresult_redis_key}")
    private String frontend_catresult_redis_key;

    @Override
    public void insertItemCategory(CatResult catResult) {
        redisTemplate.opsForValue().set(frontend_catresult_redis_key, catResult);
    }

    @Override
    public CatResult selectItemCategory() {
        return (CatResult) redisTemplate.opsForValue().get(frontend_catresult_redis_key);
    }

}
