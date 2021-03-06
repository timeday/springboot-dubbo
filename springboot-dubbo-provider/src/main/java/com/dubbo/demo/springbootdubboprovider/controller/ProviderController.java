package com.dubbo.demo.springbootdubboprovider.controller;

import com.dubbo.demo.springbootdubboapi.enetity.User;
import com.dubbo.demo.springbootdubbocommon.service.UserService;
import com.dubbo.demo.springbootdubboprovider.localservice.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController {


    @Autowired
    private ProviderService providerService;

    @Autowired
    private UserService userService;

    @RequestMapping("/getProviderInfor")
    public String getProviderInfor(){

        return providerService.getProviderName();
    }

    @RequestMapping("/getUserList")
    public List<User> getUserList(){
        return userService.findListUser();
    }

}
