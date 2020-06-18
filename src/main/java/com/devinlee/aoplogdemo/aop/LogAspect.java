package com.devinlee.aoplogdemo.aop;

import com.alibaba.fastjson.JSON;
import com.devinlee.aoplogdemo.annotation.Log;
import com.devinlee.aoplogdemo.vo.SystemLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * aop实现日志记录
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 线程安全
     */
    private static final ThreadLocal<Date> DATE_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 通过注解设置切入点
     */
    @Pointcut("@annotation(com.devinlee.aoplogdemo.annotation.Log)")
    public void pointcut() {

    }

    /**
     * 环绕通知
     * 环绕通知()=前置+目标方法执行+后置通知
     * 核心:joinPoint.proceed()执行之前相当@Before前置通知
     * joinPoint.proceed()执行之后相当@After后置通知
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        //记录执行前的时间
        DATE_THREAD_LOCAL.set(new Date());
        //proceed方法就是用于启动目标方法执行的，在此之前相当于前置通知，之后相当于后置通知
        Object proceed = joinPoint.proceed();

        //获取被增强的方法相关信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //添加日志
        SystemLog systemLog = new SystemLog();

        systemLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        systemLog.setParams(JSON.toJSONString(joinPoint.getArgs()));
        systemLog.setReturnValues(JSON.toJSONString(proceed));
        systemLog.setBeginTime(DATE_THREAD_LOCAL.get());
        systemLog.setEndTime(new Date());
        systemLog.setDurationTime((systemLog.getEndTime().getTime() - systemLog.getBeginTime().getTime()) / 1000L);

        //获取注解信息
        Log log = signature.getMethod().getAnnotation(Log.class);

        systemLog.setDesc(log.desc());
        systemLog.setLevel(log.level());
        systemLog.setOperateType(log.operateType().name());
        systemLog.setRemark(log.remark());

        //保存日志
        System.out.println(systemLog.toString());
        return proceed;
    }
}
