package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.Page;

import java.util.List;

public interface HouseService {
    public int addHouse(House house);
    public PageInfo<House>getHouseByUid(Page page,Integer uid);
    //查看用户的房屋信息
    public House getOneHouse(String hid);
    //修改用户的房屋信息
    public int updateHouse(House house);
    //逻辑删除 房屋信息
    int delHouse(String id,Integer isdel);
    /*
     *通过审核状态查询房屋的信息
     * 如果ispass=1 表示已审核
     * 如果ispass=0表示未审核
     */
    PageInfo<House> getIsPassHouse(Page page,Integer ispass);
    //修改房屋修改审核状态  0 未审核 1 已审核
    int updateState(Integer ispass,String id);
    //出租房屋批量修改已审核状态
    int updateMoreState(String[] ids);
    //批量修改未审核出租房屋信息
    int updateNoPassMoreState(String[] ids);
    //查询所有的条件的出租房屋信息
    PageInfo<House>getSelectHouse(HouseCondition condition);
}
