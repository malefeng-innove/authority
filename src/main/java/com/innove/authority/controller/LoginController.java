package com.innove.authority.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.innove.authority.bean.dto.request.LoginRequest;
import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.dto.response.UserDetailResponse;
import com.innove.authority.bean.enums.RuntimeErrorEnum;
import com.innove.authority.service.UserService;
import com.innove.authority.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@Api(value = "/login", tags = { "登录" })
public class LoginController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UserService service;


    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParam(name = "LoginRequest", value = "用户登录请求包")
    @PostMapping("/login")
    public Response<UserDetailResponse> login(@RequestBody LoginRequest request){
        Response<UserDetailResponse> response = new Response();
        //获取传入数据
        String userName = request.getUserName();
        String password = request.getPassWord();
        String verCode = request.getVerCode();
        //获取token对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject currentUser = SecurityUtils.getSubject();
        //校验验证码
        String verifyCode = (String) currentUser.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(StringUtil.isNotBlank(verifyCode)&&(StringUtil.isBlank(verCode)||(!verCode.equals(verifyCode)))){
            return response.error(RuntimeErrorEnum.INVALID_VER_CODE_ERROR);
        }
        try{
            //校验账号密码
            subject.login(token);
            UserDetailResponse userEntity = service.detail(userName);
            response.setData(userEntity);
        } catch (UnknownAccountException e){
            return response.error(RuntimeErrorEnum.INVALID_USER_NAME_ERROR);
        } catch (IncorrectCredentialsException e){
            return response.error(RuntimeErrorEnum.INVALID_PSW_ERROR);
        }
        return response.success(200,"登陆成功");
    }

    @GetMapping("/wechatLogin/{userName}")
    public Response wechatLogin(@PathVariable("userName") String userName){
        Response response = new Response();
        UserDetailResponse userEntity = service.detail(userName);
        if(userEntity==null){
            return response.error(RuntimeErrorEnum.INVALID_USER_NAME_ERROR);
        }
        com.innove.authority.config.UsernamePasswordToken token = new com.innove.authority.config.UsernamePasswordToken(userName);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        response.setData(userEntity);
        return response.success(200,"登陆成功");
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping("/logout")
    public Response logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return new Response().success(200,"注销成功");
    }

    /**
     * 获取验证码
     */
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @GetMapping("/getVerCode")
    public void getVerCode() throws IOException {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = defaultKaptcha.createText();
        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // create the image with the text
        BufferedImage bi = defaultKaptcha.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 未登录时跳转接口
     * @return
     */
    @ApiIgnore
    @GetMapping("/toLogin")
    public Response toLogin(){
        return new Response().error(RuntimeErrorEnum.INVALID_UNLOGIN_ERROR);
    }

    /**
     * 无权限时跳转接口
     * @return
     */
    @ApiIgnore
    @GetMapping("/unAuth")
    public Response unAuth(){
        return new Response().error(RuntimeErrorEnum.UNAUTH_ERROR);
    }

}
