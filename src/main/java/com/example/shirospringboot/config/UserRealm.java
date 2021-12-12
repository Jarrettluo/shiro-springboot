package com.example.shirospringboot.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");

        //用户名 ，密码  数据中取
        String name="qingfeng";
        String password="123456";

        //创建令牌
        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;

        //判断传入的数据
        if ( !usertoken.getUsername().equals(name)){
             return null;//抛出异常 UnknownAccountException
        }
        //密码认证，shiro做
        return new SimpleAuthenticationInfo("",password,"");
    }
}


