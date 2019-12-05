package com.xiaoxiao.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @project_name:bz_parent
 * @date:2019/9/19:10:55
 * @author:shinelon
 * @Describe: redis 配置类
 */
@Configuration
public class RedisConfig
{
    @Bean
    public RedisTemplate<String, Object> setRedisTemplate(RedisConnectionFactory factory)
    {
//创建 RedisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
//设置序列化器
//创建 Redis 中的 value 的序列化器
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//创建 Redis 中的 key 的序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//设置 Redis 中的 String 类型的 value 的序列化器
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//设置 Redis 中的 Hash 类型的 value 的序列化器
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//设置 Redis 中的 String 类型的 key 的序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
//设置 Redis 中的 Hash 类型的 key 的序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
