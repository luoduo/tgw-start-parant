package com.tgw.redis.lock;

//import com.egls.cashloan.hades.common.constant.RedisKeyPrefixConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

/**
 * <p>
 * Redis 分布式锁，目前只提供三个基础方法:<br>
 * 一个使用默认过期时间 100 秒的lock()<br>
 * 一个可指定过期时间的 lock()<br>
 * * * 获取锁接口会尝试获取锁,返回 {@link Optional},业务需要判断如果为空则表示获取锁失败。<br>
 * 一个释放锁的 unLock() 方法:释放锁操作不需要返回值，每次调用都返回成功，存在以下几种情况:<br>
 * * 业务A释放锁的时候锁已经因为过期而释放<br>
 * * 业务A释放的时候锁已经已为过期而释放，并且此时又被业务B获取，业务A的锁已经释放<br>
 * * 业务A的锁没有因为过期而被释放，此时删除对应的 key。<br>
 * * 注意：对于前两种情况多是因为锁的过期设置的太短而导致的,需要业务获取锁的时候指定合适的过期时间。<br>
 *
 * <p>
 * <p/>
 *
 * @author sunfeilong   (sunfeilong@eglsgame.com)
 * @version V1.0
 * @date 2019/8/26 14:26
 */
@Slf4j
public class RedisDistributedLock {
    
    /**
     * 分布式锁前缀
     */
    private static final String DISTRIBUTED_LOCK_PREFIX = "DISTRIBUTE:LOCK";
    
    
    /**
     * 默认key过期时间，单位秒
     */
    private static final Long DEFAULT_KEY_EXPIRE_TIME = 100L;

    /**
     * /**
     * Redis脚本
     */
    private static RedisScript<String> LOCK_SCRIPT;
    private static RedisScript<Boolean> UN_LOCK_SCRIPT;
    
    /**
     * 初始化加载脚本文件
     *
     *  (单例)应该在应用上下文中配置一个DefaultRedisScript 的单例，避免在每个脚本执行的时候重复创建脚本的SHA1.
     */
    static {
        try {
            LOCK_SCRIPT = RedisScript.of(new ResourceScriptSource(new ClassPathResource("redis/lock.lua")).getScriptAsString(), String.class);
            UN_LOCK_SCRIPT = RedisScript.of(new ResourceScriptSource(new ClassPathResource("redis/unlock.lua")).getScriptAsString(), Boolean.class);
        } catch (IOException e) {
            log.error("加载配置文件初出错");
        }
    }


    /**
     * 获取锁，使用默认过期时间(100秒)
     *
     * @param redisTemplate    redis 连接
     * @param resourceIdentify 资源标识
     * @return 锁Id，用于释放锁,返回为空表示所获取失败
     */
    public static Optional<String> lock(RedisTemplate<String, String> redisTemplate, String resourceIdentify) {
        log.info("获取资源锁，使用默认过期时间，资源标识:{}, 过期时间:{}", resourceIdentify, DEFAULT_KEY_EXPIRE_TIME);
        return lock(redisTemplate, resourceIdentify, DEFAULT_KEY_EXPIRE_TIME);
    }

    /**
     * 获取锁
     *
     * @param redisTemplate    redis 连接
     * @param resourceIdentify 资源标识
     * @param expireTime       过期时间, 单位:秒
     * @return 锁Id，用于释放锁,返回为空表示所获取失败
     */
    public static Optional<String> lock(RedisTemplate<String, String> redisTemplate, String resourceIdentify, long expireTime) {
        log.info("获取资源锁，使用自定义过期时间，资源标识:{}, 过期时间:{}", resourceIdentify, expireTime);
        String result = redisTemplate.execute(LOCK_SCRIPT, Collections.singletonList(getKey(resourceIdentify)), String.valueOf(expireTime), getLockIdKeyName());
        if (StringUtils.isEmpty(result)) {
            log.info("获取资源锁，获取失败。使用自定义过期时间，资源标识:{}, 锁ID:{}, 过期时间:{}", resourceIdentify, result, expireTime);
            return Optional.empty();
        } else {
            log.info("获取资源锁，获取成功。使用自定义过期时间，资源标识:{}, 锁ID:{}, 过期时间:{}", resourceIdentify, result, expireTime);
            return Optional.of(result);
        }
    }


    /**
     * 释放锁
     *
     * @param redisTemplate    redis 连接
     * @param resourceIdentify 资源标识ID
     * @param lockId           获取锁成功之后返回的锁ID
     */
    public static void unLock(RedisTemplate<String, String> redisTemplate, String resourceIdentify, String lockId) {
        log.info("释放锁，资源标识：{}", resourceIdentify);
        redisTemplate.execute(UN_LOCK_SCRIPT, Collections.singletonList(getKey(resourceIdentify)), lockId);
    }

    /**
     * 获取资源ID，使用{}确保分布式锁的key，被散列到集群的同一台机器上
     *
     * @param resourceIdentify 资源表示
     * @return key
     */
    private static String getKey(String resourceIdentify) {
        String key = String.format("{%s}%s", DISTRIBUTED_LOCK_PREFIX, resourceIdentify);
        return key;
    }

    /**
     * 获取锁ID的key name，用于redis记录lock id
     *
     * @return lock id key name
     */
    private static String getLockIdKeyName() {
        String keyName = String.format("{%s}%s", DISTRIBUTED_LOCK_PREFIX, "ID");
        return keyName;
    }
}
