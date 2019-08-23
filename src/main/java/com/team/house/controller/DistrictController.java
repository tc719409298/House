package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @RequestMapping("/getDistrict")
    @ResponseBody
    public List<District> getDistrict(){
        List<District> list = districtService.getAllDistrict();
        return list;
    }
    //区域分页
    @RequestMapping("/getDistrictPage")
    @ResponseBody
    public HashMap<String,Object>getDistrictPage(Page page){
        PageInfo<District> pageinfo = districtService.getDistrictByPage(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageinfo.getTotal());
        map.put("rows",pageinfo.getList());
        return map;
    }
    //添加区域信息
    @RequestMapping("/addDistrict")
    @ResponseBody
    public String addDistrict(District district){
        int i=districtService.addDistrict(district);
        return "{\"result\":"+i+"}";
    }
    //查询单条区域信息
    @RequestMapping("/selectOneDistrict")
    @ResponseBody
    public District selectOneDistrict(int id){
        District district = districtService.selectOneDistrict(id);
         return district;
    }
    //修改区域信息
    @RequestMapping("/updateDistrict")
    @ResponseBody
    public String updateDistrict(District district){
        int i = districtService.updateDistrict(district);
        return "{\"result\":"+i+"}";
    }
    //删除单条信息
    @RequestMapping("/delOneDistrict")
    @ResponseBody
    public String delOneDistrict(Integer id){
        System.out.println(id);
        try {
            int i = districtService.delOneDistrict(id);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            System.out.println("出错了");
        }
        return "{\"result\":0}";
    }
    //批量删除区域信息
    @RequestMapping("/delMoreDistrict")
    @ResponseBody
    public String delMoreDistrict(String ids){
        System.out.println(ids);
        String[] arrys= ids.split(",");
        Integer[]nums=new Integer[arrys.length];
        for (int i=0;i<arrys.length;i++){
            nums[i]=new Integer(arrys[i]);        }

            int i = districtService.delMOreDistrict(nums);
            return "{\"result\":"+i+"}";


    }

}
