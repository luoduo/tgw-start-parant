package com.tgw.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching //开启redis缓存功能
public class TgwRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TgwRedisApplication.class, args);
    }

}
