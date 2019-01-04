package com.dubbo.demo.springbootdubbocommon.service;

import com.dubbo.demo.springbootdubboapi.enetity.User;

import java.util.List;


public interface OrderService {
	 List<User> initOrder(String userId);

}
