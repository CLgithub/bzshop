package com.cl.common.reids.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 配置序列化器
 * @Author l
 * @Date 2021/6/30 16:43
 */
@SpringBootConfiguration
public class RedisConfig {


    /**
     * 配置redisTemplate序列化器
     * @param redisConnectionFactory 配置文件中redis相关信息都在其中
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> setRedisTemplate(RedisConnectionFactory redisConnectionFactory){


        // 0 创建RedisTemplate
        RedisTemplate<String, Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 1 创建redis中的value的序列化器
        //  设置序列化器 默认的序列化器会消耗大量内存，自定义为json的，减小内存消耗，呆会做个实验，若不自定义，redis中没有json类型，会变成uuid形式
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper=new ObjectMapper();
        // PropertyAccessor.ALL可以访问所有属性 JsonAutoDetect.Visibility.ANY json自动检测
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 2 创建redis中的key的序列化器
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();

        // 3 指定序列化器
        // 设置redis中的string类型的value的序列化器
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // 设置redis中的hash类型的value的序列化器
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 设置Redis中的String类型的key的序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 设置redis中的hash类型的key的序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.afterPropertiesSet(); //创建对象以后，才取设置
        return redisTemplate;
    }
}
