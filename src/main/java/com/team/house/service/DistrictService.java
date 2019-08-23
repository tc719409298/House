package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.utils.Page;

import java.util.List;

public interface DistrictService {
    //查询所有的区域信息
     List<District>getAllDistrict();
     //分页查询地区信息
    public PageInfo<District>getDistrictByPage(Page page);
    //新增区域信息
     int addDistrict(District district);
     //查看单条信息
     public District selectOneDistrict(int id);
     //修改区域信息
      int updateDistrict(District district);
      //删除单条信息
     int delOneDistrict(Integer id);
     //批量删除区域信息
     public int delMOreDistrict(Integer[]ids);

    interface StreetService {
    }
}
