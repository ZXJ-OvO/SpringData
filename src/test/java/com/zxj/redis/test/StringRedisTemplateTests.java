package com.zxj.redis.test;

import com.alibaba.fastjson2.JSON;
import com.zxj.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

/**
 * fastjson2参考：https://alibaba.github.io/fastjson2/
 */
@SpringBootTest
public class StringRedisTemplateTests {

    /**
     * StringRedisTemplate继承自RedisTemplate，要求key和value都是String类型
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 测试String类型数据
     */
    @Test
    void testString() {

        // 写入一条String类型数据
        stringRedisTemplate.opsForValue().set("name", "曹操");

        // 获取String数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    /**
     * 测试Java对象数据  对象的序列化与反序列化存在严重的性能问题
     */
    @Test
    void testObject() {
        // 创建对象 没有自动序列化了，需要手动new对象
        User user1 = new User("关羽", 30);

        // 手动序列化 Java对象转成JSON数据
        String jsonUser1 = JSON.toJSONString(user1);

        // 将JSON数据写入Redis
        stringRedisTemplate.opsForValue().set("user:200",jsonUser1);

        // 获取数据
        String jsonUser2 = stringRedisTemplate.opsForValue().get("user:200");

        // 手动反序列化 JSON数据转成Java对象
        User user2 = JSON.parseObject(jsonUser2, User.class);

        System.out.println("user2 = " + user2);
    }

    /**
     * 测试哈希数据序列化与反序列化
     */
    @Test
    void testHash(){
        stringRedisTemplate.opsForHash().put("user:300","name","刘备");
        stringRedisTemplate.opsForHash().put("user:300","age","18");

        // entries()方法返回指定key的所有键值对
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:300");
        System.out.println("entries = " + entries);
    }

}
