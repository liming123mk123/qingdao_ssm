package com.itqf.service;

import com.itqf.pojo.User;


import java.util.List;

/**
 * author: 赵伟风
 * date: 2019/6/28 12:11
 * description:
 */

public interface UserService {

    List<User> findUserAll();
    User login(String uname,String upwd);
    int register(String uname,String upwd);
    String getPassword(String uname);
    int getUid(String ume);
}
