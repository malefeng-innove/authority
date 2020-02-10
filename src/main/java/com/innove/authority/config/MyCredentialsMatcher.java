package com.innove.authority.config;

import com.innove.authority.util.StringUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @Description shiro匹配器
 * @Author mlf
 * @data 2020-01-09 17:44
 */
public class MyCredentialsMatcher extends HashedCredentialsMatcher {

    /**
     * 自定义加密方式
     * @param token
     * @param info
     * @return
     */
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        try {
            Object tokenHashedCredentials =  StringUtil.MD5(new String((char[]) token.getCredentials()));
            return this.equals(tokenHashedCredentials, info.getCredentials());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
/*
    /**
     * 原始加密方式
     * @param token
     * @param info
     * @return
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object tokenHashedCredentials = this.hashProvidedCredentials(token, info);
        Object accountCredentials = this.getCredentials(info);
        return this.equals(tokenHashedCredentials, accountCredentials);
    }
    */
}
