package com.tgw.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/11/28 16:55
 */
@Configuration
@MapperScan("com.tgw.mybatis.mapper")
public class MybatisPlusConfig {
    
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}