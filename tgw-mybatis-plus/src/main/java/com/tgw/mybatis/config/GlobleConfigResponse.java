package com.tgw.mybatis.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.baomidou.mybatisplus.core.enums.IEnum;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置文件
 */
@ControllerAdvice
public class GlobleConfigResponse implements ResponseBodyAdvice<Object> {
    
    
    /**
     * 枚举中包含的属性类型列表
     */
    private static String[] TYPE_STR = {"class java.lang.String","class java.lang.Boolean","class java.lang.Integer","class java.lang.Long","class java.lang.Double","int","long","boolean","double"};
    
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        
        SerializeFilter[] serializeFilters = new SerializeFilter[]{
                /***
                 * 枚举处理 将枚举转换为json对象 返回
                */
                new ValueFilter() {
                    @Override
                    public Object process(Object object, String name, Object value) {
                        JSONObject enumJson = null;
                        
                        if (value instanceof IEnum) {
                            enumJson = new JSONObject();
                            try {
                                Class enumClz = value.getClass();
                                Field[] fields = enumClz.getDeclaredFields();
                                for (Field field : fields) {
                                    Type genericType = field.getGenericType();
                                    if(Arrays.asList(TYPE_STR).contains(genericType.toString())){
                                        Method m = value.getClass().getMethod(
                                                    "get" + getMethodName(field.getName()));
                                        Object val = m.invoke(value);
                                        enumJson.put(field.getName(),val);
                                    }
                                }
    
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return enumJson;
                        }
                        return value;
                    }
                }
        };
        return JSON.parse(JSON.toJSONString(o, serializeFilters  ,  SerializerFeature.WriteNullStringAsEmpty , SerializerFeature.WriteDateUseDateFormat , SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteDateUseDateFormat));
    }
    
    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String fildeName){
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}