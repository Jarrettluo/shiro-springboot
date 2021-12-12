package com.example.shirospringboot.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class ShiroConfig {
    //三大核心要素：

    //ShiroFilterFactoryBean
    //第三步:
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        System.out.println(defaultWebSecurityManager);
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加Shiro的内置过滤器
        /*anon：无需认证就可以访问
        authc：必须认证了才能让问
        user：必须拥有，记住我功能，才能访问
        perms：拥有对某个资源的权限才能访问
        role：拥有某个角色权限才能访问*/

        //设置登录权限,登录拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/user/add", "authc");
//        filterMap.put("/user/update", "authc");
        filterMap.put("/user/*", "authc");
        System.out.println("======");
        System.out.println(bean.toString());

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
        bean.setLoginUrl("/toLogin");

        return bean;
    }

    //DafaultWebSecurityManager
    //第二步：
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建realm对象，需要自定义类
    //第一步：
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
