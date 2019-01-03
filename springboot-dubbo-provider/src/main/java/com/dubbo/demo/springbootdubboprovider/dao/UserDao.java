package com.dubbo.demo.springbootdubboprovider.dao;

import com.dubbo.demo.springbootdubboapi.enetity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    public List<User> findListUser();
}
