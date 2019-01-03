package com.dubbo.demo.springbootdubboprovider.remotelyservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.demo.springbootdubbocommon.service.UserService;
import com.dubbo.demo.springbootdubboapi.enetity.User;
import com.dubbo.demo.springbootdubboprovider.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(version = "1.0.0",interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findListUser() {
        return userDao.findListUser();
    }
}
