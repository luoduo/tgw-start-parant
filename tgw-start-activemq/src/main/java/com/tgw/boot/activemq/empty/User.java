package com.tgw.boot.activemq.empty;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.beans.Transient;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/6 10:51
 */
@Data
public class User implements Serializable {
    
    
    private String aaa ;
    
//    @Transient
    private String password ;
    
    @Override
    public String toString(){
        String password = (new ReflectionToStringBuilder(this) {
            // 注意这里为了表达上的简洁用了匿名内部类.
            @Override
            protected boolean accept(Field f) {
                return super.accept(f) && !f.getName().equals("password");
            }
        }).toString();
        return password;
    }
}
