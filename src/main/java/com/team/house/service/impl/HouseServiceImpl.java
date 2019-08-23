package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        System.out.println(house);

        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUid(Page page, Integer uid) {
        //开启分页，带分页条件
        page.setRows(3);
        PageHelper.startPage(page.getPage(),page.getRows());
        //查询用户发布的所有房屋信息
        List<House>list=houseMapper.getHouseByUid(uid);
        return new PageInfo<House>(list);
    }

    @Override
    public House getOneHouse(String hid) {
        return houseMapper.getOneHouse(hid)               ;
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int delHouse(String id, Integer isdel) {
        House house = new House();
        house.setId(id);
        house.setIsdel(isdel);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getIsPassHouse(Page page, Integer ispass) {
        //开启分页
        PageHelper.startPage(page.getPage(),page.getRows());
        //查询未审核的房屋信息
        List<House>list=houseMapper.getIsPassHouse(ispass);

        return new PageInfo<House>(list);
    }

    @Override
    public int updateState(Integer ispass, String id) {
        House house = new House();
        house.setId(id);
        house.setIspass(ispass);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int updateMoreState(String[] ids) {

        return houseMapper.updateMoreState(ids);
    }

    @Override
    public int updateNoPassMoreState(String[] ids) {
        return houseMapper.updateNoPassMoreState(ids);
    }

    @Override
    public PageInfo<House> getSelectHouse(HouseCondition condition) {
        //开启分页
        PageHelper.startPage(condition.getPage(),condition.getRows());
        //查询所有的带条件的以审核出租房信息
        List<House> list = houseMapper.getSelectHouse(condition);
        return new PageInfo<House>(list);
    }
}
