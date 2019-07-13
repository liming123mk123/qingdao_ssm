package com.itqf.service;

import com.itqf.mapper.UserMapper;
import com.itqf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: 赵伟风
 * date: 2019/6/28 12:12
 * description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findUserAll() {
        List<User> list = userMapper.selectUserAll();
        return list;
    }

    public User login(String uname, String upwd) {
        User user = userMapper.login(uname, upwd);
       return user;
    }

    public int register(String uname, String upwd) {
        int i = userMapper.register(uname,upwd);
        return i;
    }

    public String getPassword(String uname) {
        return userMapper.getPassword(uname);
    }

    public int getUid(String uname) {
        return userMapper.getUid(uname);
    }
}
