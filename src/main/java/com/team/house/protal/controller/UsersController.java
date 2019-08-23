package com.team.house.protal.controller;

import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("usersController2")
@RequestMapping("/page/")
public class UsersController {
    @Autowired
    private UsersService usersService;
    /* 检查 用户名是否存在*/
    @RequestMapping("getUserName")
    @ResponseBody
 public String getUserName(String name){
        int temp = usersService.getUserName(name);
        return "{\"result\":"+temp+"}";
    }
    /*注册xinyongh*/
 @RequestMapping("reg")
    public String reg(Users users){
     int temp = usersService.addUser(users);
     if (temp>0){
         return "login";//进入登录页面
     }else {
         return "regs";//进入注册页面
     }
 }
 //通过用户名 跟密码登入
    @RequestMapping("loginByNamePsw")
    public String loginByNamePsw(String name, String password, Model model, HttpSession session){
        Users user = usersService.login(name, password);
        if (user==null){
            model.addAttribute("info","用户名跟密码不正确");
            return "login";
        }else {
            session.setAttribute("logininfo",user);
            session.setMaxInactiveInterval(3000);//设置30s
            return "redirect:getHouseByUid";
        }
    }
}
