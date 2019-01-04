package com.dubbo.demo.springbootdubbocommon.service;

import com.dubbo.demo.springbootdubboapi.enetity.User;
import com.dubbo.demo.springbootdubbocommon.service.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * 本地存根
 * 远程服务后，客户端通常只剩下接口，而实现全在服务器端，但提供方有些时候想在客户端也执行部分逻辑，
 * 比如：做 ThreadLocal 缓存，提前验证参数，调用失败后伪造容错数据等等，此时就需要在 API 中带上 Stub，
 * 客户端生成 Proxy 实例，会把 Proxy 通过构造函数传给 Stub [1]，然后把 Stub 暴露给用户，Stub 可以决定要不要去调 Proxy。
 */
public class UserServiceStub implements UserService {

    private final UserService userService;

    // 构造函数传入真正的远程代理对象
    public UserServiceStub(UserService userService) {
        this.userService = userService;
    }

    public List<User> findListUser( ) {
        // 此代码在客户端执行, 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
        try {
            System.out.println("本地存根");
            return userService.findListUser();
        } catch (Exception e) {
            // 你可以容错，可以做任何AOP拦截事项
            return Arrays.asList(new User());
        }
    }
}
