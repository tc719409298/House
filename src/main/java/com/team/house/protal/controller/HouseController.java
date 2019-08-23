package com.team.house.protal.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.*;
import com.team.house.service.DistrictService;
import com.team.house.service.HouseService;
import com.team.house.service.StreetService;
import com.team.house.service.TypeService;
import com.team.house.utils.HouseCondition;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/page")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StreetService streetService;
    @RequestMapping("/goFaBu")
    public String goFaBu(Model model){
        //所有房屋类型
        List<Type> types = typeService.getAllType();
        //看看所有区域
        List<District> districts = districtService.getAllDistrict();

        model.addAttribute("types",types);
        model.addAttribute("districts",districts);

        return "fabu";
    }
     @RequestMapping("/addHouse")
    public String addHouse( @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile,House house,HttpSession session){
        /* System.out.println("文件名："+pfile.getOriginalFilename());
         System.out.println("文件大小："+pfile.getSize());
         System.out.println("文件类型"+pfile.getContentType());*/
         try {
             //文件上传的路径
             String path="f:/images/";
             String filename = pfile.getOriginalFilename();
              String expname=filename.substring(filename.lastIndexOf("."));
             System.out.println(expname);
             String imageName=System.currentTimeMillis()+ UUID.randomUUID().toString()+expname;
             File file=new File(path+imageName);
             pfile.transferTo(file);//图片上传
             //保存信息到数据库
             house.setPath(imageName);
             //用户的id
             Users user = (Users) session.getAttribute("logininfo");
             house.setUserId(user.getId());

             house.setId(System.currentTimeMillis()+"");
             houseService.addHouse(house);
             return "redirect:getHouseByUid";

         }catch (Exception e){
             e.printStackTrace();
             e.getMessage();
         }
         return "fabu";

     }
     @RequestMapping("/getHouseByUid")
    public String getHouseByUid(Model model, Page page,HttpSession session){
         Users user = (Users) session.getAttribute("logininfo");
         PageInfo<House> pageInfo = houseService.getHouseByUid(page, user.getId());
         model.addAttribute("pageInfo",pageInfo);
         return "guanli";
     }
     //查询单条用户信息
     @RequestMapping("/getOneHouse")
     public String getOneHouse(Model model,String id){
         /*System.out.println("房屋编号"+id);*/
         House house = houseService.getOneHouse(id);
         System.out.println(house);
         model.addAttribute("house",house);
         return "updatefabu";
     }
     //修改用户的信息
     @RequestMapping("/updateHouse")
    public String updateHouse(@RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile ,House house,String oldImage){
         String filename = pfile.getOriginalFilename();
        try {
            if (filename.equals("")) {
                houseService.updateHouse(house);
                return "redirect:getHouseByUid";
            } else {
                //文件上传的的路径
                String path="f:/images/";

                filename=filename.substring(filename.lastIndexOf("."));
                String imageName=System.currentTimeMillis()+UUID.randomUUID().toString()+filename;
                System.out.println(imageName);
                File file = new File(path + imageName);
                //文件上传
                pfile.transferTo(file);
                //文件路径上传到数据库中 更新数据库
                house.setPath(imageName);
                houseService.updateHouse(house);
                 //删除以往的旧图片
                File file1 = new File(path + oldImage);
                file1.delete();

                return "redirect:getHouseByUid";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
     }
    //逻辑删除房屋信息
    @RequestMapping("/delHouse")
    public String delHouse(String id){
        houseService.delHouse(id,1);
        return "redirect:getHouseByUid";
    }
    //恢复逻辑删除的房屋信息
    @RequestMapping("/recoverHouse ")
    @ResponseBody
    public String recoverHouse(String id){
        int i = houseService.delHouse(id, 0);
        return "{\"result\":"+i+"}";
    }
    //查询带条件的所有出租房
    @RequestMapping("/getSelectHouse")
    public String getSelectHouse(HouseCondition condition, Model model){
        PageInfo<House> housePageInfo = houseService.getSelectHouse(condition);
        model.addAttribute("housePageInfo",housePageInfo);
        model.addAttribute("condition",condition);
        return "list";
    }
    //查询单个用户详情
    @RequestMapping("/getSingleHouse")
    public String getSingleHouse(Model model,String id){
        /*System.out.println("房屋编号"+id);*/
        House house = houseService.getOneHouse(id);
        System.out.println(house);
        model.addAttribute("house",house);
        return "details";
    }
}
