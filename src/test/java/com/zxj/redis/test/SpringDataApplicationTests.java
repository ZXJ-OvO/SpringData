package com.zxj.redis.test;

import com.zxj.redis.pojo.User;
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
 * ---
 * 默认采用了JDK的序列化规则
 * 对于String类型的数据，可以采用StringRedisInnSerializer实现序列化
 * 对于Java对象类型的数据，可以采用Jackson2JsonRedisSerializer实现序列化
 * ---
 * 使用redisTemplate的自动序列化与反序列化要求key为String类型，value为Object类型
 */
@SpringBootTest
class SpringDataApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 测试String类型数据
     */
    @Test
    void testString() {
        System.out.println(redisTemplate.toString());

        // 写入一条String类型数据
        redisTemplate.opsForValue().set("name", "庞涓");

        // 获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    /**
     * 测试Java对象数据
     */
    @Test
    void testObject() {
        // 写入数据 序列化成JSON数据
        redisTemplate.opsForValue().set("user:100", new User("张飞", 30));

        // 获取数据 反序列化成Java对象
        User user = (User)redisTemplate.opsForValue().get("user:100");
        System.out.println("user = " + user);
    }

}
