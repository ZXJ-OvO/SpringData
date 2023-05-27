package com.zxj.redis.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redisTemplate.opsForValue()   操作字符串
 * redisTemplate.opsForHash()    操作hash
 * redisTemplate.opsForList()    操作list
 * redisTemplate.opsForSet()     操作set
 * redisTemplate.opsForZSet()    操作有序set
 * <p>
 * 默认采用了JDK的序列化规则
 * 对于String类型的数据，可以采用StringRedisInnSerializer实现序列化
 * 对于Java对象类型的数据，可以采用Jackson2JsonRedisSerializer实现序列化
 */
@SpringBootTest
class SpringDataApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void contextLoads() {
        System.out.println(redisTemplate.toString());

        // 写入一条String类型数据
        redisTemplate.opsForValue().set("name", "庞涓");

        // 获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

}
