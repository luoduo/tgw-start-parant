package com.tgw.service.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/2 16:43
 */
@WebService
public interface WebServiceDemoService {
    
    @WebMethod
    String hello(@WebParam(name = "name") String name);
    
    
}
