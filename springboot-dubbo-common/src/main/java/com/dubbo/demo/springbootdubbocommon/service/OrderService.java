package com.dubbo.demo.springbootdubbocommon.service;

import com.dubbo.demo.springbootdubboapi.enetity.User;

import java.util.List;


public interface OrderService {


	public List<User> initOrder(String userId);

}
