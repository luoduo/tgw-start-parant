package com.tgw.boot.activemq.empty;

import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/6 10:53
 */
class UserTest {
    
    @Test
    void getAaa() {
    
        User user = new User();
        user.setAaa("bbb");
        String s = user.toString();
        System.out.println(s);
    }
}