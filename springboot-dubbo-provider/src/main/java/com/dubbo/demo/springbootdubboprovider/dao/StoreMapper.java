package com.dubbo.demo.springbootdubboprovider.dao;

import com.dubbo.demo.springbootdubboapi.enetity.Store;
import com.dubbo.demo.springbootdubboapi.enetity.StoreExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreMapper {

    int countByExample(StoreExample example);

    int deleteByExample(StoreExample example);


    int deleteByPrimaryKey(String id);


    int insert(Store record);


    int insertSelective(Store record);


    List<Store> selectByExample(StoreExample example);


    Store selectByPrimaryKey(String id);


    int updateByExampleSelective(@Param("record") Store record, @Param("example") StoreExample example);


    int updateByExample(@Param("record") Store record, @Param("example") StoreExample example);


    int updateByPrimaryKeySelective(Store record);


    int updateByPrimaryKey(Store record);
}
