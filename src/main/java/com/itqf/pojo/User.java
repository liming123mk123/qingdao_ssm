package com.itqf.pojo;

import java.io.Serializable;

/**
 * author: 赵伟风
 * date: 2019/6/28 12:12
 * description:
 */
public class User implements Serializable {
    private int uid;
    private String uname;
    private String upwd;


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
}
