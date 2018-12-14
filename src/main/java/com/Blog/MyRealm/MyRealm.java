package com.Blog.MyRealm;

import com.Blog.Entity.Blogger;
import com.Blog.Service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 自定义realm
 */
public class MyRealm extends AuthorizingRealm{

    @Autowired
    private BloggerService bloggerService;

    /**
     * 当前用户的授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 当前用户的认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        Blogger blogger = bloggerService.getByName(username);
        if (blogger != null)
        {
            SecurityUtils.getSubject().getSession().setAttribute("currentUser",blogger);
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(blogger.getUsername(),blogger.getPassword(),"blogger");
            return authenticationInfo;
        }
        else
        {
            return null;
        }

    }
}
