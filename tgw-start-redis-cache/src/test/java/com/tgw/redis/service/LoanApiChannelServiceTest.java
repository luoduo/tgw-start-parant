package com.tgw.redis.service;

import com.alibaba.fastjson.JSON;
import com.tgw.redis.TgwRedisApplication;
import com.tgw.redis.config.RedisUtils;
import com.tgw.redis.model.LoanApiChannel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/11/28 20:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TgwRedisApplication.class)
class LoanApiChannelServiceTest {
    
    @Resource
    LoanApiChannelService loanApiChannelService;
    @Resource
    RedisUtils redisUtils;
    @Resource
    RedisTemplate redisTemplate;
    @Test
    void findAll() {
        System.out.println("++++++++++++++"+JSON.toJSONString(loanApiChannelService.findAll()));
    }
    
    @Test
    void testRedisTemplate() {
        List<LoanApiChannel> all = loanApiChannelService.findAll();
        System.out.println(all);
//        for (LoanApiChannel loanApiChannel : all) {
//            redisTemplate.opsForHash().put("loan:api:channel", loanApiChannel.getId()+"", loanApiChannel);
//        }
        
    }
//    @Test
//    void test() {
//        loanApiChannelService.findAll();
////        System.out.println(redisUtils.get("SMS:TEMPLATE:APP:10"));
//        System.out.println(redisUtils.get("SMS:TEMPLATE:APP:10"));
//    }
    @Test
    void testGet() {
        redisUtils.get("SMS:TEMPLATE:APP:10");
    }
    @Test
    void testRedisUtils() {
        redisUtils.set("123","456",5L);
//        System.out.println(redisUtils.get("SMS:TEMPLATE:APP:10"));
//        System.out.println(redisUtils.get("SMS:TEMPLATE:APP:10"));
    }
}