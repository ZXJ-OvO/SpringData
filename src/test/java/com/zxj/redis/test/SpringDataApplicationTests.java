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

        // 写入一条String类型数据
        redisTemplate.opsForValue().set("name", "庞涓");

        // 获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    /**
     * 测试Java对象数据  对象的序列化与反序列化存在严重的性能问题
     * 序列化Java对象时，还会带上对象的字节码文件，反序列化时根据字节码文件反序列化得到对象
     * 使用StringRedisTemplate进行序列化，要求key和value都是String类型
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
