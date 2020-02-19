package com.innove.authority.config;

import com.innove.authority.bean.enums.DictCodes;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    public MyHashedCredentialsMatcher() {
    }

    public MyHashedCredentialsMatcher(String hashAlgorithmName) {
        super(hashAlgorithmName);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        if (uToken.getLoginType().equals(DictCodes.LOGIN_TYPE_PC)) {
            return super.doCredentialsMatch(token, info);
        } else {
            return true;
        }
    }
}
