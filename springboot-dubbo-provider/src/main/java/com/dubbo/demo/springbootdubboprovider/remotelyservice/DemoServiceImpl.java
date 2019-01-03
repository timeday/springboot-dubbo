package com.dubbo.demo.springbootdubboprovider.remotelyservice;

import com.alibaba.dubbo.config.annotation.Service;

import com.dubbo.demo.springbootdubbocommon.service.DemoService;
import com.dubbo.demo.springbootdubboprovider.localservice.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Service(version = "1.0.0",interfaceClass = DemoService.class)
@Component
public class DemoServiceImpl implements DemoService {

    @Autowired
    private ProviderService providerService;

    @Override
    public String selectList() {
        return providerService.getProviderName();
    }
}
