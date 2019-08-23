package com.team.house.mapper;

import com.team.house.entity.House;
import com.team.house.entity.HouseExample;
import com.team.house.utils.HouseCondition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    //查询用户发布的出租房
    List<House>getHouseByUid(Integer uid);
    //查询单个用户的的出租房
    House getOneHouse(String hid);
    //逻辑删除 用户的出租房信息
    int delHouse(String id,Integer isdel);
    //查询所有为审核的房屋的信息
    List<House> getIsPassHouse(Integer ispass);
    //出租房屋批量修改已审核状态
    int updateMoreState(String[]ids);
    //批量修改未审核出租房屋信息
    int updateNoPassMoreState(String[] ids);
    //查询所有的条件的出租房屋信息
    List<House>getSelectHouse(HouseCondition condition);
    //查询单个用户的房屋信息
    House getSingeleHouse(String hid);
}