package com.wenruo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @Author WenRuo
 * @Date 2020/4/28 10:22
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {

    /* ShiroFilterFactoryBean */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /* 设置安全管理器 */
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /* 添加shiro的内置过滤器 */
        /**
         * anon：无需认证就可以访问
         * authc：必须认证才能访问
         * user：必须拥有 记住我才能访问
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/my/add", "authc");
        filterMap.put("/my/update", "authc");
//        filterMap.put("/my/*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        /* 设置登录的请求 */
        shiroFilterFactoryBean.setLoginUrl("/my/toLogin");

        return shiroFilterFactoryBean;
    }



    /* DefaultWebSecurityManager */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        /* 关联UserRealm */
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    /* 创建 realm 对象 , 需要自定义*/
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }


}
