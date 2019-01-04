package com.dubbo.demo.springbootdubboapi.enetity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private String address;

    private Integer sex;

    private Integer del;

    public User() {
    }

    public User(Integer id, String name, String address, Integer sex, Integer del) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.del = del;
    }
}
