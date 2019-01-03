package com.dubbo.demo.springbootdubbocustomer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableDubbo
@EnableHystrix
@SpringBootApplication
public class SpringbootDubboCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboCustomerApplication.class, args);
    }

}

