package com.itqf.service;

import com.itqf.mapper.LeaveMapper;
import com.itqf.pojo.Leave;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;


    public int addLeave(Leave leave) {
      leaveMapper.addLeave(leave);
        int lid =leave.getLid();
        //启动流程实例
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("student",leave.getUser().getUname());
        map.put("teacher","admin");
        runtimeService.startProcessInstanceByKey("leave",lid+"",map);
       //学生完成任务
        String taskID = taskService.createTaskQuery().taskAssignee(leave.getUser().getUname()).singleResult().getId();
        taskService.complete(taskID);
        return lid;
    }

    public List<Leave> leaveList(String username) {
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(username).list();
        List<String> ids = new ArrayList<String>();
        for(Task t :taskList){
           String bkey = runtimeService.createProcessInstanceQuery() .processInstanceId(t.getProcessInstanceId()).singleResult().getBusinessKey();
           ids.add(bkey);
        }
        List<Leave> leaveList = leaveMapper.leaveList(ids);
        return leaveList;
    }

    public int updateLeave(int lid) {
       /* runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(lid+"")
                .singleResult()*/
        String taskId = taskService.createTaskQuery().processInstanceBusinessKey(lid + "").singleResult().getId();
        taskService.complete(taskId);
        return leaveMapper.updateLeave(lid);
    }
}
