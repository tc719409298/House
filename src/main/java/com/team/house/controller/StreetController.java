package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("/getStreetByDid")
    @ResponseBody
    public HashMap<String ,Object>getStreetByDid(Integer did, Page page){
        System.out.println(did);
        PageInfo<Street> pageInfo = streetService.getStreetByDid(page, did);
        HashMap<String, Object> map = new HashMap<>();
         map.put("total",pageInfo.getTotal());
         map.put("rows",pageInfo.getList());
         return map;
    }
    @RequestMapping("/addStreet")
    @ResponseBody
    public String addStreet(Street street){
        int temp = streetService.addStreet(street);
        return "{\"result\":"+temp+"}";

    }



}
