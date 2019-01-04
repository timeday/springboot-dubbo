package com.dubbo.demo.springbootdubbocustomer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.demo.springbootdubbocommon.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "/demo")
@Api(value = "/demo", tags = "用户信息列表查询")
public class DemoConsumerController {

    @Reference(version = "1.0.0",retries = 2)
    DemoService demoService;

    @ApiOperation(value = "用户列表", notes = "查询用户列表")
    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    public String sayHello( ) {
        System.out.println("-----消费者调用-----");
        return demoService.selectList();
    }

}
