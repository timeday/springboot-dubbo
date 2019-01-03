package com.dubbo.demo.springbootdubbocustomer.controller;

import com.dubbo.demo.springbootdubboapi.enetity.User;
import com.dubbo.demo.springbootdubbocommon.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(value = "/uac", tags = "订单用户")
public class OrderController {

	@Autowired
	OrderService orderService;

	@ApiOperation(value = "订单用户", notes = "订单用户")
	@ResponseBody
	@RequestMapping("/initOrder")
	public List<User> initOrder(@RequestParam("uid")String userId) {
		return orderService.initOrder(userId);
	}

}
