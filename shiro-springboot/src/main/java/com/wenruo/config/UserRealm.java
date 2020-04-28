package com.wenruo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.security.sasl.AuthorizeCallback;

/**
 * @ClassName UserRealm
 * @Description 自定义的UserRealm
 * @Author Lenovo
 * @Date 2020/4/28 10:25
 * @Version 1.0
 **/
@Slf4j
public class UserRealm extends AuthorizingRealm {

    /* 授权 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行了 =====> 授权方法 doGetAuthorizationInfo");
        return null;
    }

    /* 认证 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行了 =====> 认证方法 doGetAuthenticationInfo");

        /* 用户名， 密码~ 数据库中取 */
        String name = "root";
        String password = "root";

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        if (!token.getUsername().equals(name)){
            return null; /* 抛出异常 UnknownAccountException 用户名的异常*/
        }

        /* 密码认证， shiro自己做 */
        return new SimpleAuthenticationInfo("",password,"");
    }
}
