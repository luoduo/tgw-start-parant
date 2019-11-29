package com.tgw.redis.config.redis.serializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.support.NullValue;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/11/29 17:24
 */
public class GenericJackson2JsonRedisSerializerEx implements RedisSerializer<Object> {
    
    protected GenericJackson2JsonRedisSerializer serializer = null;
    
    /**
     * 修改GenericJackson2JsonRedisSerializer 以实现兼容序列化是, LocalDateTime 时间的问题
     *
     */
    public GenericJackson2JsonRedisSerializerEx() {
        ObjectMapper om = new ObjectMapper();
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        om.registerModule((new SimpleModule())
                .addSerializer(new NullValueSerializer()));
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        this.serializer = new GenericJackson2JsonRedisSerializer(om);
    }
    
    public GenericJackson2JsonRedisSerializerEx(ObjectMapper om) {
        this.serializer = new GenericJackson2JsonRedisSerializer(om);
    }
    
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return serializer.serialize(o);
    }
    
    /**
     *
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes)  {
    
        Object deserialize = null;
        try {
            deserialize = serializer.deserialize(bytes);
        } catch (SerializationException e) {
            System.out.println("666");
            e.printStackTrace();
        }
        return deserialize;
    }
    
    
    protected class NullValueSerializer extends StdSerializer<NullValue> {
        private static final long serialVersionUID = 1999052150548658807L;
        private final String classIdentifier = "@class";
        
        NullValueSerializer() {
            super(NullValue.class);
        }
        
        @Override
        public void serialize(NullValue value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeStringField(this.classIdentifier, NullValue.class.getName());
            jgen.writeEndObject();
        }
    }
}
