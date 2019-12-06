package com.tgw.redis.lock;

import com.tgw.redis.TgwRedisApplication;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/5 10:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TgwRedisApplication.class)
class RedisDistributedLockTest {
    
    private static final String TEST_LOCK_KEY = "test";
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    RedisTemplate redisTemplate;
    
    @org.junit.jupiter.api.Test
    public void testLock() {
        //加锁
        Optional<String> lock = RedisDistributedLock.lock(stringRedisTemplate, TEST_LOCK_KEY);
        Assert.assertTrue(lock.isPresent());
        //释放锁
        RedisDistributedLock.unLock(stringRedisTemplate, TEST_LOCK_KEY, lock.get());
    }
    
    @org.junit.jupiter.api.Test
    public void main2() {
        System.out.println("333");
        redisTemplate.opsForHash().put("key2","keyHash11","金乡");
        redisTemplate.opsForHash().put("key2","keyHash12","222");
        System.out.println("444");
    }
    
    @Test
    public void testMultiThreadUnLock() {
        Long startTime = System.currentTimeMillis();
        CompletableFuture[] completableFutures = new CompletableFuture[10];
        for (int i = 0; i < completableFutures.length; i++) {
            completableFutures[i] = CompletableFuture.runAsync(() -> {
                Optional<String> lock = Optional.empty();
                while (!lock.isPresent()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock = RedisDistributedLock.lock(stringRedisTemplate, TEST_LOCK_KEY, 1);
                }
            });
        }
        CompletableFuture.allOf(completableFutures).join();
        Long endTime = System.currentTimeMillis();
        Assert.assertTrue(endTime - startTime > TimeUnit.SECONDS.toMillis(9));
    }
}