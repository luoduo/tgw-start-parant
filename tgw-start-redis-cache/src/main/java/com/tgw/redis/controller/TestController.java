package com.tgw.redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/6 10:00
 */
@RestController
public class TestController {
    
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @GetMapping("/redis")
    public Object main() {
        redisTemplate.opsForHash().put("key1","keyHash11","111");
        redisTemplate.opsForHash().put("key1","keyHash12","222");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("string1");
        strings.add("string2");
        strings.add("string3");
        redisTemplate.opsForHash().put("key1","keyHash13",strings);
        Object o = redisTemplate.opsForHash().get("key1", "keyHash13");
        return o;
//        return "success";
    }
    
    @GetMapping("/redis_string")
    public Object main2() {
        
        stringRedisTemplate.opsForValue().set("stringkey1","汉字");
        String stringkey1 = stringRedisTemplate.opsForValue().get("stringkey1");
        return stringkey1;
//        return "success";
    }
}
