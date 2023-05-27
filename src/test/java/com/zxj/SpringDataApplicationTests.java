package com.zxj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redisTemplate.opsForValue()   操作字符串
 * redisTemplate.opsForHash()    操作hash
 * redisTemplate.opsForList()    操作list
 * redisTemplate.opsForSet()     操作set
 * redisTemplate.opsForZSet()    操作有序set
 */
@SpringBootTest
class SpringDataApplicationTests {


    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        // 写入一条String类型数据
        redisTemplate.opsForValue().set("name", "zxj");

        // 获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

}
