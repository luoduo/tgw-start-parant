//package com.egls.cashloan.hades.generate;
//
//import com.egls.cashloan.tools.SecurityTool;
//
///**
// * 用户名和密码加密工具
// *
// * @author sunfeilong   (sunfeilong@eglsgame.com)
// * @version V1.0
// * @date 2019/9/26 20:42
// */
//public class UserNameAndPasswordEncodeUtil {
//
//    public static void main(String[] args) throws Exception {
//
//        String userName = "root";
//        String password = "root";
//
//        String encodeUserName = SecurityTool.encryptRsa(userName);
//        String encodePassword = SecurityTool.encryptRsa(password);
//
//        System.out.println(encodeUserName);
//        System.out.println(encodePassword);
//
//        String decodeUserName = SecurityTool.decryptRsa(encodeUserName);
//        String decodePassword = SecurityTool.decryptRsa(encodePassword);
//
//        System.out.println(decodeUserName);
//        System.out.println(decodePassword);
//    }
//}
