package com.tgw.service.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/3 10:28
 */
@WebService
public interface JRPerClit {
    @WebMethod
    String JRPerClitInfo(@WebParam(name = "JRPerClitInfos") String name);
}
