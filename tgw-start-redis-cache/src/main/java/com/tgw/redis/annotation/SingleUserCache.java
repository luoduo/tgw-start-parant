package com.tgw.redis.annotation;

import com.tgw.redis.service.LoanApiChannelService;
import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Cacheable(cacheNames = LoanApiChannelService.LOAN_ALL_CACHE, key = "#userId", unless = "#result?.isDelete = 1")
public @interface SingleUserCache {
}