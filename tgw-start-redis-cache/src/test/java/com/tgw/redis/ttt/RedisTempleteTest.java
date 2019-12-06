package com.tgw.redis.ttt;

import com.tgw.redis.TgwRedisApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/6 10:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TgwRedisApplication.class)
class RedisTempleteTest
//        extends BaseTest
{
    @Resource
    private RedisTemplate redisTemplate;
    
    
    @org.junit.jupiter.api.Test
    public void main2() {
        System.out.println("333");
        redisTemplate.opsForHash().put("key2","keyHash11","金乡");
        redisTemplate.opsForHash().put("key2","keyHash12","222");
        System.out.println("444");
    }
}
