package com.dubbo.demo.springbootdubbocustomer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.demo.springbootdubboapi.enetity.User;
import com.dubbo.demo.springbootdubbocommon.service.OrderService;
import com.dubbo.demo.springbootdubbocommon.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 		1）、导入dubbo依赖（2.6.2）\操作zookeeper的客户端(curator)
 * 		2）、配置服务提供者
 *
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 * @author lfy
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Reference(loadbalance="random",timeout=1000) //dubbo直连
	UserService userService;

	@HystrixCommand(fallbackMethod="hello")
	@Override
	public List<User> initOrder(String userId) {
		//int i=1/0;
		//1、查询用户的收货地址
		List<User> addressList = userService.findListUser();
		return addressList;
	}


}
