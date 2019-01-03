package com.dubbo.demo.springbootdubbocustomer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cloud.api.interfaces.DemoService;
import com.cloud.api.model.dto.CloudUacUserDto;
import com.cloud.dubbo.wrapper.Wrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "/uac")
@Api(value = "/uac", tags = "用户信息列表查询")
public class DemoConsumerController {


    @Reference(version = "1.0.0")
    DemoService demoService;

    /*@Reference
    CloudService cloudService;*/

    @ApiOperation(value = "用户列表", notes = "查询用户列表")
    @RequestMapping(value = "/user_list", method = RequestMethod.POST)
    public Wrap sayHello(CloudUacUserDto dto) {
        System.out.println("-----消费者调用-----");
        Wrap temp = demoService.selectList(dto);
        return temp;
    }

    /*@RequestMapping("/sayHi/{name}")
    public String sayHi(@PathVariable("name") String name) {
        System.out.println("-----消费者调用-----");
        return cloudService.sayHi(name);
    }*/

}
