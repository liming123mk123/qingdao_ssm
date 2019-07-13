package com.itqf.mapper;


import com.itqf.pojo.Profile;
import com.itqf.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author: 赵伟风
 * date: 2019/6/27 9:33
 * description:
 */

public interface UserMapper {

        List<User> selectUserAll();
        User login(@Param("uname") String uname, @Param("upwd" ) String upwd);
        int register(@Param("uname") String uname, @Param("upwd" ) String upwd);
        String getPassword(String uame);
        int getUid(String uanme);

}
