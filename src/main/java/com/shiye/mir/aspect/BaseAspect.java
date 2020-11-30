package com.shiye.mir.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 切入点基类
 * @author fangshaozu
 */
public class BaseAspect {
    /**
     * 切入点 - API接口调用
     */
    @Pointcut("execution(public * com.shiye.mir.controller..*.*(..)) && args(request)")
    public void apiCallPoint(HttpServletRequest request) {
    }

    /**
     * 获取当前拦截点对应的方法
     */
    public Method getCurrentMethod(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }
}
