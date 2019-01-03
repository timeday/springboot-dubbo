package com.dubbo.demo.springbootdubboprovider.aop;



import com.dubbo.demo.springbootdubboapi.enetity.LogInfo;
import com.dubbo.demo.springbootdubbocommon.utils.ClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * 公共前端拦截器，处理日志及异常信息
 * Created by cloud-micro-service.
 */
@Slf4j
@Order(1)
@Aspect
@Component
public class HttpAspect {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    private LogInfo logInfo = new LogInfo();

    public static String getExceptionAllInformation(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
        }
        return ret;
    }

    @Pointcut("execution(public * com.dubbo.demo.springbootdubboprovider.*.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        threadLocal.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logInfo.setAddress(request.getRequestURL().toString());
        logInfo.setHttpMethod(request.getMethod());
        logInfo.setIp(ClientUtil.getClientIp(request));
        // 获取真实的ip地址
        logInfo.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logInfo.setArgs(Arrays.toString(joinPoint.getArgs()));
        log.info("【request】—> {}", logInfo.toString());
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        try {
            Object ob = proceedingJoinPoint.proceed();
            return ob;

        } catch (Exception e) {
            log.error("服务器内部错误,{}", logInfo.toString());
            logInfo.setError(getExceptionAllInformation(e));
            return null;

        }
    }

    @AfterReturning(pointcut = "log()", returning = "object")
    public void doAfterReturn(Object object) {
        logInfo.setResult(object.toString());
        long time = System.currentTimeMillis() - threadLocal.get();
        log.info("【耗时】—> {}", time);
        logInfo.setTime(time);
        log.info("【response】-> {}", logInfo.toString());
    }
}
