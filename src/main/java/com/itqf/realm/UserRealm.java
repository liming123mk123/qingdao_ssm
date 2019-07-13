package com.itqf.realm;

import com.itqf.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
   @Autowired
   private UserService userService;
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       //获取当前用户名
        String uname =(String)principalCollection.getPrimaryPrincipal();
       //根据用户名查询张虎彪，获取到该账户对应的角色列表
        Set<String> roles = new HashSet<String>();
        roles.add("role1");
        Set<String> permissions = new HashSet<String>();
        permissions.add("user:add");
        permissions.add("user:update");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String uname = (String)authenticationToken.getPrincipal();
        //数据库中根据用户名查询密码
        String upwd = userService.getPassword(uname);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(uname,upwd,"UserRealm");

        return simpleAuthenticationInfo;
    }
}
