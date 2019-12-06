package com.tgw.redis.ttt;

import com.tgw.redis.BaseTest;
import com.tgw.redis.TgwRedisApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RedisTempleteTest
//        extends BaseTest
{
    
    @Resource
    private RedisTemplate redisTemplate;
    @Test
    public void main() {
        redisTemplate.opsForHash().put("key1","keyHash11","111");
        redisTemplate.opsForHash().put("key1","keyHash12","222");
    }
}
