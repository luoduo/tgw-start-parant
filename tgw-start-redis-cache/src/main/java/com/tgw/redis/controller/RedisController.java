package com.tgw.redis.controller;

import com.tgw.redis.service.LoanApiChannelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/11/29 12:17
 */
@RestController
public class RedisController {
    
    @Resource
    LoanApiChannelService loanApiChannelService;
    @GetMapping("/get")
    public Object get(){
        return loanApiChannelService.findAll();
    }
}
