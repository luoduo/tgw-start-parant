package com.tgw.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgw.redis.config.redis.serializer.GenericJackson2JsonRedisSerializerEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

/**
 * 想要序列化成json格式的缓存数据，可以自定义一个redis的config类，设置序列化规则
 * @author tanggewei@eglsgame.com
 * @date 2019/11/29 15:25
 */
@Configuration
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisConfig
//        extends CachingConfigurerSupport
{
    
    @Autowired
    private RedisConnectionFactory factory;
    
    
    
//    /**
//     * 使用默认RedisSerializer 或JdkSerializationRedisSerializer
//     *      优点: 不用自己手动指定对象的Class。开发人员可读性差
//     *      缺点: 数据占用内存大，是JSON格式的5倍左右，这样就会消耗redis服务器的大量内存。
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        redisConnectionFactory = this.factory;
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//
//
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
//        redisTemplate.afterPropertiesSet();
//
//        return redisTemplate;
//    }
    
    
    /**
     * 使用 GenericJackson2JsonRedisSerializer / Jackson2JsonRedisSerializer 序列化方式(替代默认的 JdkSerializationRedisSerializer)
     *      优点: 优点是速度快，序列化后的字符串短小精悍。占用内存少
     *      缺点: 但缺点也非常致命，那就是此类的构造函数中有一个类型参数，必须提供要序列化对象的类型信息(.class对象)。
     *            一旦类名或包名修改后, 将会抛出异常(no such class found) 反序列化失败, 系统异常
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        redisConnectionFactory = this.factory;
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
    
//        // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(om);
    
        /**
         * GenericJackson2JsonRedisSerializer 存在LocalDateTime 反序列化时异常
         *      (Cannot construct instance of `java.time.LocalDateTime`)
         * 通过自定义GenericJackson2JsonRedisSerializerEx 解决LocalDateTime 反序列化问题
         */
//        RedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializer serializer = new GenericJackson2JsonRedisSerializerEx();
//        RedisSerializer serializer = new JdkSerializationRedisSerializer();
        
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
//        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
    
    
    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }
}