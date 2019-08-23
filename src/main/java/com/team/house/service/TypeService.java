package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.utils.Page;

import java.util.List;

public interface TypeService {
    //查询所有的区域信息
     List<Type>getAllType();
     //分页查询地区信息
    public PageInfo<Type>getTypeByPage(Page page);
    //新增区域信息
     int addType(Type type);
     //查看单条信息
     public Type selectOneType(int id);
     //修改区域信息
      int updateType(Type type);
      //删除单条信息
     int delOneType(Integer id);
     //批量删除区域信息
     public int delMOreType(Integer[] ids);
}
