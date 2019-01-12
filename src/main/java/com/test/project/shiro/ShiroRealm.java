package com.test.project.shiro;

import com.test.project.entity.Identity;
import com.test.project.entity.Permission;
import com.test.project.entity.User;
import com.test.project.mapper.IdentityMapper;
import com.test.project.mapper.UserMapper;
import com.test.project.mapper.UserPermissionMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    private UserMapper userMapper;

    private IdentityMapper identityMapper;

    private UserPermissionMapper userPermissionMapper;

    /**
     * 获取用户角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();

        logger.info("用户" + username + "获取权限-----ShiroRealm.doGetAuthorizationInfo");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //获取用户角色集
        List<Identity> roleList = identityMapper.findByUsername(username);
        Set<String> roleSet = new HashSet<>();
        for (Identity role : roleList) {
            roleSet.add(role.getName());
        }
        simpleAuthorizationInfo.addRoles(roleSet);

        //获取用户权限集
        List<Permission> permissionList = userPermissionMapper.findByUserName(username);
        Set<String> permissionSet = new HashSet<>();
        for (Permission permission : permissionList) {
            permissionSet.add(permission.getName());
        }
        simpleAuthorizationInfo.addStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 验证用户的合法性
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户输入的用户名和密码
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        System.out.println("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");

        //通过用户名到数据库查询用户信息
        User user = userMapper.queryUserByUsername(userName);

        if (user == null) {
            throw new UnknownAccountException("用户名或者密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或者密码错误！");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setIdentityMapper(IdentityMapper identityMapper) {
        this.identityMapper = identityMapper;
    }

    @Autowired
    public void setUserPermissionMapper(UserPermissionMapper userPermissionMapper) {
        this.userPermissionMapper = userPermissionMapper;
    }
}
