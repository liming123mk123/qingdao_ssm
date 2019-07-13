package com.itqf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itqf.pojo.Leave;
import com.itqf.pojo.Profile;
import com.itqf.pojo.User;
import com.itqf.service.LeaveService;
import com.itqf.service.ProfileService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LeaveController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private LeaveService leaveService;
    @RequestMapping("/leave")
    public String leave(){
        return "leave";
    }
    @RequestMapping("/addLeave")
public String addLeave(Leave leave, HttpSession session){
        User user = (User)session.getAttribute("user");
        leave.setUser(user);
        int i = leaveService.addLeave(leave);
        //根据自己项目
        return "redirect:selectRecordByUId";
}
    @RequestMapping("/leaveList")
    public  String leaveList(Model model,HttpSession session){
        User user = (User)session.getAttribute("user");
        String uname = user.getUname();
        List<Leave> leaveList = leaveService.leaveList(uname);
        model.addAttribute("leaveList",leaveList);
        return "leave_list";
        }
    @RequestMapping("/updateLeave")
    @ResponseBody
    public String updateLeave(int lid){
        System.out.println(lid);
        System.out.println("-----------------11-----------");
        leaveService.updateLeave(lid);
        System.out.println("updateLeave-------------------jieshu");
        return "success";
    }
}
