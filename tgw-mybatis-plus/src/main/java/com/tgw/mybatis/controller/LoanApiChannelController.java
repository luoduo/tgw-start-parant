package com.tgw.mybatis.controller;


import com.tgw.mybatis.service.LoanApiChannelService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tgw
 * @since 2019-11-28
 */
@RestController
//@RequestMapping("/loan/")
public class LoanApiChannelController {
    
    @Resource
    private LoanApiChannelService loanApiChannelService;
    
    @GetMapping("/get")
    public Object get(){
        return loanApiChannelService.list();
    }
    
    @GetMapping("/delete/{id}")
    public Object delete(@PathVariable("id")Long id){
        return loanApiChannelService.removeById(id);
    }
    
    @GetMapping("/getById/{id}")
    public Object getById(@PathVariable("id")Long id){
        return loanApiChannelService.getById(id);
    }
    

}
