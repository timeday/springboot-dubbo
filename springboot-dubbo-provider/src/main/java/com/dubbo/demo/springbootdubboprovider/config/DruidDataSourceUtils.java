package com.dubbo.demo.springbootdubboprovider.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dubbo.deml.springbootdubbocommon.utils.AesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: dubbo-springboot
 * @description:
 * @author: z.hw
 * @create: 2018-09-29 15:53
 **/
public class DruidDataSourceUtils extends DruidDataSource {
    private static final Logger logger = LoggerFactory.getLogger(DruidDataSourceUtils.class);

    public DruidDataSourceUtils() {
    }

    public void setUsername(String username) {
        logger.debug("Set username: {}", username);
        super.setUsername(AesUtils.decrypt(username));
    }

    public void setPassword(String password) {
        logger.debug("Set password: {}", password);
        super.setPassword(AesUtils.decrypt(password));
    }
 }
