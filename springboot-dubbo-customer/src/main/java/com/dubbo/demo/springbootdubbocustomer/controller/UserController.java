package com.dubbo.demo.springbootdubbocustomer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.demo.springbootdubboapi.enetity.User;
import com.dubbo.demo.springbootdubbocommon.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "/user", tags = "用户")
public class UserController {

    @Reference(version = "1.0.0",timeout=1000,retries = 2,validation = "true")
    private UserService userService;

    @ApiOperation(value = "用户", notes = "用户")
    @RequestMapping("/getUserList")
    public List<User> getUserList(){
        return userService.findListUser();
    }


}
