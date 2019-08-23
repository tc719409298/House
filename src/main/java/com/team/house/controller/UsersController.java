package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import com.team.house.utils.UsersCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @RequestMapping("/getUsers")
    @ResponseBody
    public Map<String ,Object>getUsers(UsersCondition usersCondition){
        PageInfo<Users> pageInfo = usersService.getUsers(usersCondition);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //新增用户
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(Users users){
        int temp = usersService.addUser(users);
        return "{\"result\":"+temp+"}";
    }
    //查询单个用户信息
    @RequestMapping("/getOneUsers")
    @ResponseBody
    public Users getOneUsers(Integer id){
        Users users = usersService.getOneUsers(id);
        return users;
    }
}
