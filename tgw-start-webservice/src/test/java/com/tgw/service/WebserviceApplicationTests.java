package com.tgw.service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class WebserviceApplicationTests {
    
    
    @Test
    public void testSend1() {
        
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //{http://webservice.business.mixpay.com}WebServiceDemoService
        Client client = dcf.createClient("http://localhost:8088/services/WebServiceDemoService?name");
        
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        Object[] objects = new Object[0];
        try {
            
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("sendMessage", "wyj");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
