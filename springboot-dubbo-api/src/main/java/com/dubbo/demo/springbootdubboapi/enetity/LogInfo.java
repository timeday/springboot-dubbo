package com.dubbo.demo.springbootdubboapi.enetity;


import lombok.Data;

@Data
public class LogInfo implements java.io.Serializable{

    private String id;

    private String address;

    private String httpMethod;

    private String ip;

    private String classMethod;

    private String args;

    private Long time;

    private String error;

    private String result;

}
