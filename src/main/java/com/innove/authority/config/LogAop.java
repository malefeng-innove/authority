package com.innove.authority.config;

import com.innove.authority.bean.dto.response.Response;
import com.innove.authority.bean.exception.MyException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Pointcut("execution(* com.innove.authority.controller.*.*.*(..))")
    private void methodPointcut(){}

    @Around("methodPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder log = new StringBuilder();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            Object res = onException(throwable);
            logger.error(JSON.toJSONString(res));
            return onException(throwable);
        }
        log.append("\n-----------------------------start-----------------------------");
        log.append(String.format("%nURL：%s",request.getRequestURL()));
        log.append(String.format("%nMethod：%s",request.getMethod()));
        log.append(String.format("%nIP：%s",request.getRemoteAddr()));
        log.append(String.format("%n请求报文：%s",JSON.toJSONString(joinPoint.getArgs())));
        log.append(String.format("%n响应报文：%s", JSON.toJSONString(result)));
        log.append("\n-----------------------------end-----------------------------");
        logger.info(log.toString());
        return result;
    }

    private Object onException(Throwable ex){
        if(ex instanceof MyException){
            MyException e = (MyException) ex;
            return new Response().error(-100,e.getErrorCode(),e.getErrorMsg());
        }else{

            return new Response().error(65336,null,ex.getMessage());
        }
    }
}
