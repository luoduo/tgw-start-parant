package com.tgw.redis.service;

import com.alibaba.fastjson.JSON;
import com.tgw.redis.TgwRedisApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/11/28 20:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TgwRedisApplication.class)
class LoanApiChannelServiceTest {
    
    @Resource
    LoanApiChannelService loanApiChannelService;
    @Test
    void findAll() {
        System.out.println("++++++++++++++"+JSON.toJSONString(loanApiChannelService.findAll()));
    }
}