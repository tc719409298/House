package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller(value ="houseController2")
@RequestMapping("/admin")
public class HouseController {
    @Autowired
    private HouseService houseService;
    //查询未审核的出租房信息
    @RequestMapping("/getNoPassHouse")
    @ResponseBody
    public Map<String,Object>getNoPassHouse(Page page){
        PageInfo<House> pageInfo = houseService.getIsPassHouse(page, 0);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //查询已审核的出租房信息
    @RequestMapping("/getYesPassHouse")
    @ResponseBody
    public Map<String,Object>getYesPassHouse(Page page){
        PageInfo<House> pageInfo = houseService.getIsPassHouse(page, 1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //修改出租房未审核状态
    @RequestMapping("/updateNoPassState")
    @ResponseBody
    public String updateNoPassState(String id){
        int i = houseService.updateState(1, id);
        return "{\"result\":"+i+"}";
    }
    //修改出租房已审核状态
    @RequestMapping("/updateYesPassState")
    @ResponseBody
    public String updateYesPassState(String id){
        int i = houseService.updateState(0, id);
        return "{\"result\":"+i+"}";
    }
    //批量修改已审核状态
    @RequestMapping("/updateYesPASSMoreState")
    @ResponseBody
    public String updateYesPASSMoreState(String ids){

        System.out.println(ids);
        String[] arrys= ids.split(",");
        int i = houseService.updateMoreState(arrys);


        return "{\"result\":"+i+"}";


    }
    //批量修改未审核状态
    @RequestMapping("/updateNoPassMoreState")
    @ResponseBody
    public String updateNoPassMoreState(String ids){

        System.out.println(ids);
        String[] arrys= ids.split(",");
        int i = houseService.updateNoPassMoreState(arrys);


        return "{\"result\":"+i+"}";


    }

}
