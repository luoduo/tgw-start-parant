package com.tgw.mybatis.controller;

import com.tgw.mybatis.TgwMybatisPlusApplication;
import com.tgw.mybatis.service.LoanApiChannelService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/11/28 19:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TgwMybatisPlusApplication.class)
class LoanApiChannelControllerTest {
    
    
    @Resource
    LoanApiChannelService loanApiChannelService;
    @Test
    void getById() {
        System.out.println(loanApiChannelService.getById(1));
    }
}