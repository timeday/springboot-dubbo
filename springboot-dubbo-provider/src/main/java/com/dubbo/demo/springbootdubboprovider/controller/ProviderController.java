package com.dubbo.demo.springbootdubboprovider.controller;

import com.dubbo.demo.springbootdubboprovider.localservice.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {


    @Autowired
    private ProviderService providerService;

    @RequestMapping("/getProviderInfor")
    public String getProviderInfor(){

        return providerService.getProviderName();
    }

}
