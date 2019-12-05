package com.dbgo.webservicedemo.service;

import com.dbgo.webservicedemo.model.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/3 11:18
 */
@WebService
public interface UserService {
    @WebMethod
    String getName(@WebParam(name = "userId") String userId);
    
    @WebMethod
    User getUser(String userI);
    
    @WebMethod
    ArrayList<User> getAlLUser();
}