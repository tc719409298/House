package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.utils.UsersCondition;

public interface UsersService {
    //分页带条件查询users
    PageInfo<Users>getUsers(UsersCondition usersCondition);
    int addUser(Users users);
     public Users getOneUsers(Integer id);
     int getUserName(String name);
     Users login(String name,String password);
}
