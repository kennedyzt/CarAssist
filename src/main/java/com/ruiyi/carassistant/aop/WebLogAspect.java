package com.ruiyi.carassistant.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.ruiyi.carassistant.utils.LoggerUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebLogAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.ruiyi.carassistant.web.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        LoggerUtil.info("URL : " + request.getRequestURL().toString());
        LoggerUtil.info("HTTP_METHOD : " + request.getMethod());
        LoggerUtil.info("IP : " + request.getRemoteAddr());
        LoggerUtil.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LoggerUtil.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        LoggerUtil.info("RESPONSE : " + ret);
        LoggerUtil.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }

}
