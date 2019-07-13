package com.itqf.service;

import com.itqf.pojo.Leave;
import com.itqf.pojo.User;

import java.util.List;

/**
 * author: 赵伟风
 * date: 2019/6/28 12:11
 * description:
 */

public interface LeaveService {
    public int addLeave(Leave leave);
    //老师登录获取需要审批的请假条列表
    public List<Leave> leaveList(String username);
    //改变请假条状态
    public int updateLeave(int lid);


}
