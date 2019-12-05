package com.tgw.service.webservice;

import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/2 16:43
 */
@Service
@WebService(
        serviceName = "JRPerClit", // 与接口中指定的name一致
        targetNamespace = "http://webservice.service.tgw.com/", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.tgw.service.webservice.WebServiceDemoService" // 接口地址
)
public class WebServiceDemoServiceImpl implements WebServiceDemoService {
    
    @Override
    public String JRPerClitInfo(String JRPerClitInfos) {
        System.out.println(JRPerClitInfos);
        return "hello:" + JRPerClitInfos;
    }
    
    @Override
    public List<String> getAll() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        return strings;
    }
}
