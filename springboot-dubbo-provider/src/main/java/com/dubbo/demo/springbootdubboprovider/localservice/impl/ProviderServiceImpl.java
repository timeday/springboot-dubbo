package com.dubbo.demo.springbootdubboprovider.localservice.impl;

import com.dubbo.demo.springbootdubboprovider.localservice.ProviderService;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService {


    @Override
    public String getProviderName() {
        return "服务方";
    }
}
