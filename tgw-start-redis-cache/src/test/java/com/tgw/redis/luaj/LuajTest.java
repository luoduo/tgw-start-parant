package com.tgw.redis.luaj;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/5 15:48
 */
@Slf4j
public class LuajTest {
    
    private static final String LUA_URL = "redis/hello.lua";
//    public static void main(String[] args) {
//
//        String luaStr = "print 'hello,world!'";
//        Globals globals = JsePlatform.standardGlobals();
//        LuaValue chunk = globals.load(luaStr);
//        chunk.call();
//
//    }
    
//    public static void main(String[] args) {
//        try {
//            new LuajTest().main1();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
    
    /**
     * 无返回对象情况下java调用lua函数
     * @throws URISyntaxException
     */
    @Test
    public void main1() throws URISyntaxException {
        String luaFileName = this.getClass().getClassLoader().getResource(LUA_URL).toURI().getPath();
        Globals globals = JsePlatform.standardGlobals();
        globals.loadfile(luaFileName).call();
        LuaValue func = globals.get(LuaValue.valueOf("helloWithoutTranscoder"));
        String result = func.call().toString();
        log.error("result---"+result);
    }
    
    /**
     * 无参lua函数
     * @throws URISyntaxException
     */
    @Test
    public void main2() throws URISyntaxException {
        String luaFileName = getClass().getClassLoader().getResource(LUA_URL).toURI().getPath();
        Globals globals = JsePlatform.standardGlobals();
        LuaValue transcoderObj = globals.loadfile(luaFileName).call();
        LuaValue func = transcoderObj.get(LuaValue.valueOf("hello"));
        String result = func.call().toString();
        log.error("result---"+result);
    }
    /**
     * 有参（参数为字符串数据）lua函数
     * @throws URISyntaxException
     */
    @Test
    public void main3() throws URISyntaxException {
        String luaFileName = getClass().getClassLoader().getResource(LUA_URL).toURI().getPath();
        Globals globals = JsePlatform.standardGlobals();
        LuaValue transcoderObj = globals.loadfile(luaFileName).call();
        LuaValue func = transcoderObj.get(LuaValue.valueOf("test"));
        String result = func.call(LuaValue.valueOf("sky")).toString();
        log.error("result---"+result);
    }
    /**
     * 返回一个lua对象的lua函数
     * （如何将返回来luaValue直接转换为json字符串和java对象，还没有找到解决方案，有思路的大佬请留言）
     */
    @Test
    public void main4() throws URISyntaxException {
        String luaFileName = getClass().getClassLoader().getResource(LUA_URL).toURI().getPath();
        Globals globals = JsePlatform.standardGlobals();
        LuaValue transcoderObj = globals.loadfile(luaFileName).call();
        LuaValue func = transcoderObj.get(LuaValue.valueOf("getInfo"));
        LuaValue hTable  = func.call();
        //解析返回来的table，这里按照格式，一个个参数去取
        String userId = hTable.get("userId").toString();
        LuaTable servicesTable = (LuaTable) CoerceLuaToJava.coerce(hTable.get("services"), LuaTable.class);
        List<String> servciesList = new ArrayList<>();
        for (int i = 1; i <= servicesTable.length(); i++) {
            int length = servicesTable.get(i).length();
            StringBuilder service = new StringBuilder();
            for (int j = 1; j <= length; j++) {
                service.append(servicesTable.get(i).get(j).toString());
            }
            servciesList.add(service.toString());
        }
        log.error("userId:"+userId);
        log.error("servcies:"+servciesList);
    }
    /**
     * 传入一个java对象到lua函数
     * （如果有可以将复杂model转化为luaValue的思路请留言）
     */
    @Test
    public void main5() throws URISyntaxException {
        String luaFileName = getClass().getClassLoader().getResource(LUA_URL).toURI().getPath();
        Globals globals = JsePlatform.standardGlobals();
        LuaValue transcoderObj = globals.loadfile(luaFileName).call();
        LuaValue func = transcoderObj.get(LuaValue.valueOf("readInfo"));
        //CoerceJavaToLua.coerce(javaObject) 经测试，可以直接调用该方法
        //将一个java对象转化为luaValue，但是嵌套model情况下的java对象转
        //换有问题，因此这里直接使用LuaValue手动去包装
        LuaValue luaValue = new LuaTable();
        luaValue.set("userId", "11111");
        String userId  = func.invoke(luaValue).toString();
        log.error("userId:"+userId);
    }
}
