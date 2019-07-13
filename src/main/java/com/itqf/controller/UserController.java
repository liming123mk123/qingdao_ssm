package com.itqf.controller;

import com.itqf.pojo.User;
import com.itqf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * author: 赵伟风
 * date: 2019/6/28 12:12
 * description:
 */
@Controller
@SessionAttributes(value = {"user","uid"}, types={User.class,String.class})
public class UserController {

    @Autowired
    private SecurityManager securityManager;
    @Autowired
    private UserService userService;

    @RequestMapping("l")
    public String l(){
        return "login";
    }
    @ResponseBody
    @RequestMapping("query")
    public String queryUser(){
        List<User> userAll = userService.findUserAll();
        //System.out.println("userAll = " + userAll);
        return "success";
    }
@RequestMapping(value = "unauthorize")
public String unauthorize(){
        return "unauthrorize";
}
/*    @RequestMapping("login")
    public String login(Model model, String uname,  String upwd){

       User user = userService.login(uname,upwd);
        if(user!=null){
           int uid = user.getUid();
            model.addAttribute("uid",uid);
            model.addAttribute("user",user);
            System.out.println(user);
            return "forward:selectRecordByUId";
        }
       else {
            return "login";
        }
    }*/

    @ResponseBody
    @RequestMapping("register")
    public String register(Model model,  @RequestParam(name = "uname", defaultValue ="test")String uname, @RequestParam(name = "upwd", defaultValue ="123")String upwd){
        int i = userService.register(uname,upwd);
        System.out.println(i);
        if(i!=1){
            return "fail";
        }
        else {
            return "sucess";
        }
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(User user, HttpSession session) {
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUname(), user.getUpwd());
        try {
            System.out.println("11111111111");
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()) {
                int uid = userService.getUid(user.getUname());
                session.setAttribute("uid",uid);
                session.setAttribute("user", user);
                return "forward:selectRecordByUId";
            }
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
        }

        return "login";
    }

}
