//package com.tgw.service;
//
//import com.tgw.service.webservice.WebServiceDemoService;
//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
//
////@SpringBootTest
//class WebserviceTests {
//
////    @Test
////    void contextLoads() {
////        new webservicede
////    }
////
//
//    public static void main(String[] args) {
//        WebserviceTests.main1();
////        CxfClient.main2();
//    }
//
//    /**
//     * 1.代理类工厂的方式,需要拿到对方的接口地址
//     */
//    public static void main1() {
//        try {
//            // 接口地址
//            String address = "http://127.0.0.1:8088/webservice";
//            // 代理工厂
//            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//            // 设置代理地址
//            jaxWsProxyFactoryBean.setAddress(address);
//            // 设置接口类型
//            jaxWsProxyFactoryBean.setServiceClass(WebServiceDemoService.class);
//            // 创建一个代理接口实现
//            WebServiceDemoService us = (WebServiceDemoService) jaxWsProxyFactoryBean.create();
//            // 数据准备
//            String userId = "zz";
//            // 调用代理接口的方法调用并返回结果
//            String result = us.hello(userId);
//            System.out.println("返回结果:" + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
////    /**
////     * 2：动态调用
////     */
////    public static void main2() {
////        // 创建动态客户端
////        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
////        Client client = dcf.createClient("http://127.0.0.1:9090/ws/helloWebService?wsdl");
////        // 需要密码的情况需要加上用户名和密码
////        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
////        Object[] objects = new Object[0];
////        try {
////            // invoke("方法名",参数1,参数2,参数3....);
////            objects = client.invoke("getUserName", "maple");
////            System.out.println("返回数据:" + objects[0]);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//}
