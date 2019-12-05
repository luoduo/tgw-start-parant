package com.tgw.service.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/2 16:43
 */
@WebService
public interface WebServiceDemoService {
    
    @WebMethod
    String JRPerClitInfo(@WebParam(name = "JRPerClitInfos") String JRPerClitInfos);
    @WebMethod
    List<String> getAll();
    
    
}
