package com.itqf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itqf.mapper.ProfileMapper;
import com.itqf.pojo.Profile;
import com.itqf.pojo.User;
import com.itqf.service.ProfileService;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;

  /*  @RequestMapping("queryProfile")
    public String queryProfile(Model model){
        List<Profile> profiles = profileService.selectProfileAll();
       // System.out.println("profiles:->"+ profiles);
        model.addAttribute("profiles",profiles);
        return "index";
    }*/


    @RequestMapping("queryId")
    public String queryId(Model model,@RequestParam(name = "id", defaultValue ="1") int id){

        Profile profiles = profileService.selectId(id);
        model.addAttribute("profiles",profiles);
        //System.out.println("profiles:->->>>>>"+ profiles);
        return "queryId";
    }

  /*  @RequestMapping(value = "updateProfile",produces = " text/html; charset=UTF-8",method = RequestMethod.POST)
    public String updateProfile(Model model, Profile profile){
        System.out.println("!profile!"+profile.getGender()+profile.getCareer());
        //System.out.println("开始更新--->");
       int i = profileService.updateProfile(profile);
        if(i!=1){
            return "fail";
        }
        else {
            return "forward:selectRecordByUId";
        }
    }*/

    @RequestMapping(value = "ajax")
    @ResponseBody
    public String deleteId(int id){
       // System.out.println("走ajax"+id);
        profileService.deleteProfile(id);
        JSONObject json = new JSONObject();
        json.element("resp","success");
        return  json.toString();
    }
    @RequestMapping("update")
    public String update(Model model,@RequestParam(name = "id", defaultValue ="1") int id){
        Profile profiles = profileService.selectId(id);
        model.addAttribute("profiles",profiles);
       // System.out.println("update --- profiles:->->>>>>"+ profiles);
        return "update";
    }
    @RequestMapping("queryProfile")

    public String queryProfile(Model model, HttpServletRequest request){
       // System.out.println("开始selectProfileList");
        String uid = request.getSession().getAttribute("uid").toString();
        List<Profile> profiles = profileService.selectProfileList(Integer.parseInt(uid));



        model.addAttribute("profiles",profiles);
        return "index";
    }
    @RequestMapping("selectRecordByUId")
    public String selectRecordByUId(HttpServletRequest request,Model model, @RequestParam(value = "PageNum",defaultValue = "1") Integer PageNum, @RequestParam(value = "PageSize",defaultValue = "1") Integer PageSize) {
        System.out.println("222222222222222222");
        String uid = request.getSession().getAttribute("uid").toString();
      //  System.out.println(uid+"!____===");
        System.out.println("33333333333333333");
        PageHelper.startPage(PageNum,2);
        List<Profile> profiles = profileService.selectProfileList(Integer.parseInt(uid));
        PageInfo objectPageInfo = new PageInfo(profiles);
         model.addAttribute("pageInfo", objectPageInfo);
         model.addAttribute("profiles", profiles);
         return "index";

    }

    @RequestMapping(value = "updateProfile",produces = " text/html; charset=UTF-8",method = RequestMethod.POST)
    public String updateProfile( Profile profile, MultipartFile file) throws IOException {

       String originalFilename = file.getOriginalFilename(); //获取文件的名字
        System.out.println(originalFilename+"图片名字");
        profile.setPicture("/img/"+originalFilename);
        File dir = new File("C:\\Users\\Administrator\\Desktop\\课堂笔记\\spring\\第十天_mybatis_ssm_回顾\\代码\\qingdao_ssm\\src\\main\\webapp\\img");

        if (!dir.exists())
        {
            dir.mkdirs();
        }

        File itemFile = new File(dir,originalFilename);

        file.transferTo(itemFile); //写到执行的文件

        int i = profileService.updateProfile(profile);
        if(i!=1){
            return "fail";
        }
        else {
            return "forward:selectRecordByUId";
        }
    }
}
