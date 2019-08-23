package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;
    //查询所有的房屋类型 返回跳转页面
    @RequestMapping("/getType")
    public String getType(Model model){
        List<Type> types = typeService.getAllType();
        model.addAttribute("types",types);
        return "fabu";
    }
    //查看所有的房屋类型，返回的数据源
    @RequestMapping("/getAllType")
    @ResponseBody
    public List<Type> getType(){
        List<Type> types = typeService.getAllType();

        return types;
    }

    //区域分页
    @RequestMapping("/getTypePage")
    @ResponseBody
    public HashMap<String,Object>getTypePage(Page page){
        PageInfo<Type> pageinfo = typeService.getTypeByPage(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",pageinfo.getTotal());
        map.put("rows",pageinfo.getList());
        return map;
    }
    //添加区域信息
    @RequestMapping("/addType")
    @ResponseBody
    public String addType(Type type){
        int i=typeService.addType(type);
        return "{\"result\":"+i+"}";
    }
    //查询单条区域信息
    @RequestMapping("/selectOneType")
    @ResponseBody
    public Type selectOneType(int id){
        Type Type = typeService.selectOneType(id);
         return Type;
    }
    //修改区域信息
    @RequestMapping("/updateType")
    @ResponseBody
    public String updateType(Type type){
        int i = typeService.updateType(type);
        return "{\"result\":"+i+"}";
    }
    //删除单条信息
    @RequestMapping("/delOneType")
    @ResponseBody
    public String delOneType(Integer id){
        System.out.println(id);
        try {
            int i = typeService.delOneType(id);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            System.out.println("出错了");
        }
        return "{\"result\":0}";
    }
    //批量删除区域信息
    @RequestMapping("/delMoreType")
    @ResponseBody
    public String delMoreType(String ids){
        System.out.println(ids);
        String[] arrys= ids.split(",");
        Integer[]nums=new Integer[arrys.length];
        for (int i=0;i<arrys.length;i++){
            nums[i]=new Integer(arrys[i]);        }

            int i = typeService.delMOreType(nums);
            return "{\"result\":"+i+"}";


    }

}
