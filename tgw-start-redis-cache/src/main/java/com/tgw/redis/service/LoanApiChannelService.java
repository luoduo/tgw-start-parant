package com.tgw.redis.service;

import com.tgw.redis.annotation.SingleUserCache;
import com.tgw.redis.model.LoanApiChannel;
import com.tgw.redis.repository.LoanApiChannelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
//@CacheConfig(cacheNames = "c1")
@Slf4j
public class LoanApiChannelService {
    
    public static final String LOAN_ALL_CACHE = "loan:all:cache";
    public static final String LOAN_DETAIL_CACHE = "loan:detail:cache";
    /**
     * logger instance
     */
    /**
     * 用户持久化接口注入
     */
    @Autowired
    private LoanApiChannelRepository loanApiChannelRepository;

    /**
     * 查询全部用户
     *
     * @return
     */
    @Cacheable(cacheNames = LOAN_ALL_CACHE)
    public List<LoanApiChannel> findAll() {
        return loanApiChannelRepository.findAll();
    }

    /**
     * 查询用户详情
     * 根据userId进行缓存
     * 并且年龄大于20的数据不进行缓存
     * #result?.userAge，?代表返回值为null时不做判断处理
     *
     * @param userId
     * @return
     */
    @SingleUserCache
    public Optional<LoanApiChannel> detail(Integer userId) {
        return loanApiChannelRepository.findById(userId);
    }

    /**
     * 删除用户
     * 配置@CacheEvict后会根据cacheName以及key删除redis内对应的缓存
     *
     * @param userId
     */
    @CacheEvict(cacheNames = LOAN_DETAIL_CACHE, key = "#userId")
    public void delete(Integer userId) {
        loanApiChannelRepository.deleteById(userId);
    }

    /**
     * 一次清空多个缓存
     *
     * @param firstUserId  第一个用户编号
     * @param secondUserId 第二个用户编号
     */
    @Caching(evict = {
            @CacheEvict(cacheNames = LOAN_DETAIL_CACHE, key = "#firstUserId"),
            @CacheEvict(cacheNames = LOAN_DETAIL_CACHE, key = "#secondUserId")
    })
    public void deleteTwo(Integer firstUserId, Integer secondUserId) {
        // 删除第一个用户
        loanApiChannelRepository.deleteById(firstUserId);
        // 删除第二个用户
        loanApiChannelRepository.deleteById(secondUserId);
    }

    /**
     * 更新用户基本信息
     * 根据参数进行更新用户信息
     * 配置@CachePut后会将方法返回值重新根据配置的cacheName以及key设置到redis内
     *
     * @param userId 用户编号
     * @param name   用户名称
     */
    @CachePut(cacheNames = LOAN_DETAIL_CACHE, key = "#userId")
    public Optional<LoanApiChannel> update(Integer userId, String name) {
        // 查询用户详情
        Optional<LoanApiChannel> user = loanApiChannelRepository.findById(userId);
        user.get().setApiName(name);
        // 执行更新
        loanApiChannelRepository.save(user.get());
        // 返回用户基本信息 用于更新缓存
        return loanApiChannelRepository.findById(userId);
    }
    
    
}