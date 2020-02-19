package com.innove.authority.config;

import com.innove.authority.bean.enums.DictCodes;

public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {
    private static final long serialVersionUID = 1L;

    private String captcha;
    private boolean mobileLogin;
    private String loginType; //登录方式 微信

    public UsernamePasswordToken() {
        super();
    }
    /**
     * 账号密码登录
     */
    public UsernamePasswordToken(String username, char[] password,
                                 boolean rememberMe, String host, String captcha, boolean mobileLogin) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
        this.mobileLogin = mobileLogin;
        this.loginType = DictCodes.LOGIN_TYPE_PC.getType();
    }

    public UsernamePasswordToken(String username) {
        super(username, "", false, null);
        this.loginType = DictCodes.LOGIN_TYPE_WECHAT.getType();
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isMobileLogin() {
        return mobileLogin;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
