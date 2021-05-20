package com.cloud.kjetboy.server.demo.collector;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jet
 * 采集那个方法，用什么数据，返回了什么结果
 * 1. 注解标记数据。
 * 2. ControllerAuditLogCollector 采集标注的切面数据。
 * 3. 存储采集数据。
 * 4. 完成整个审计过程。
 */
@Aspect
@Component
public class ControllerAuditLogCollector {
    private static Logger logger = LoggerFactory.getLogger(ControllerAuditLogCollector.class);
    /**
     * web请求切面拦截
     */
    @Pointcut("within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void webRequestLog() {

    }

    @Before("webRequestLog()")
    public void doBefore(JoinPoint joinPoint) {
        MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = (MethodInvocationProceedingJoinPoint)joinPoint;
        MethodSignature methodSignature = (MethodSignature)methodInvocationProceedingJoinPoint.getSignature();
        System.out.println("方法："+methodSignature.getMethod().getName());
        System.out.println("注解："+AnnotationUtils.getAnnotation(methodSignature.getMethod(), RequestMapping.class));
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(joinPoint.getSignature().getDeclaringType());
        System.out.println(joinPoint.getArgs().toString());
        System.out.println("前置增强方法，doBefore");
    }

    @After("webRequestLog()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("final增强，不管是抛出异常或者正常退出都会执行doAfter");
    }

    @AfterReturning(returning = "result", pointcut = "webRequestLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        //根据返回数据存入到数据库
        System.out.println("后置增强doAfterReturning"+ result.toString());
    }

    @AfterThrowing(pointcut = "webRequestLog()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
        System.out.println("异常抛出增强afterThrowing");
    }


}
