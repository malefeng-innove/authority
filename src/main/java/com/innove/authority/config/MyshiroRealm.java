package com.innove.authority.config;

import com.innove.authority.bean.entity.UserEntity;
import com.innove.authority.service.RoleMenuService;
import com.innove.authority.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

@Component
public class MyshiroRealm extends AuthorizingRealm {

    //密码盐值
    private static final String SALT = "";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMenuService roleMenuService;

    public MyshiroRealm() {
    }

    public MyshiroRealm(CredentialsMatcher matcher) {
        super(matcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserEntity user = (UserEntity) principals.getPrimaryPrincipal();
        String roleCode = user.getRoleCode();
        String[] menuCodes = roleMenuService.selectMenuCodeByRoleCode(roleCode);
        authorizationInfo.addRole(roleCode);
        if(menuCodes!=null&&menuCodes.length>0){
            authorizationInfo.addStringPermissions(Arrays.asList(menuCodes));
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        UserEntity user = userService.selectByName(userName);
        if(null==user){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(MyshiroRealm.SALT),
                getName()
        );
        return authenticationInfo;
    }
}
