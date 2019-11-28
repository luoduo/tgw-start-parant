package com.tgw.mybatis.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/11/28 14:33
 */

public enum ApiChannelCode implements IEnum<String> {
    
    JDQ("jdq","借点钱"),
    gnh("gnh","给你花"),
    dwd("dwd","大王贷"),
    app("app","客户端")
    ;
    
    private String value;
    
    private String desc;
    
    ApiChannelCode(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    
    @Override
    public String getValue() {
        return this.value ;
    }
    
    @JsonValue
    public String getDesc(){
        
        return this.desc;
    }
    
    
}
