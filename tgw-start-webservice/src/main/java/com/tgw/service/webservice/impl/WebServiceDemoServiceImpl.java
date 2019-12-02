package com.tgw.service.webservice.impl;

import com.tgw.service.webservice.WebServiceDemoService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/2 16:43
 */
@Service
@WebService(serviceName = "WebServiceDemoService", // 与接口中指定的name一致
        targetNamespace = "http://webservice.business.mixpay.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.tgw.service.webservice.WebServiceDemoService" // 接口地址
)
public class WebServiceDemoServiceImpl implements WebServiceDemoService {
    
    @Override
    public String hello(String name) {
        return "hello:" + name;
    }
}
