package com.dubbo.demo.springbootdubboprovider.remotelyservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.demo.springbootdubboapi.enetity.Store;
import com.dubbo.demo.springbootdubboapi.enetity.StoreExample;
import com.dubbo.demo.springbootdubbocommon.service.StoreService;
import com.dubbo.demo.springbootdubboprovider.dao.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",interfaceClass = StoreService.class,retries = 2)
@Component
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;


    public List<Store> getStoreList(){

        List<Store> stores = storeMapper.selectByExample(new StoreExample());
        return stores;
    }

}
