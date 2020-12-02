package com.shiye.mir.aspect;


import com.shiye.mir.annotation.AuthorityCheck;
import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 接口切片
 * @author fangshaozu
 */
@Order(1)
@Component
@Aspect
@Slf4j
public class AuthorityCheckAspect extends BaseAspect {

    @Resource
    private UserInfoService userInfoService;

    @Value("${app.server.host.url}")
    private String appServerHostUrl;

    private Boolean needAuthorityCheck = false;

    @Around(value="apiCallPoint(request)", argNames = "point,request")
    public Object aroundApiCallPoint(ProceedingJoinPoint point, HttpServletRequest request) throws Throwable {
        //1.检测Controller里是否含有AuthorityCheck注解
        Method method = getCurrentMethod(point);
        if (method != null) {
            needAuthorityCheck = method.isAnnotationPresent(AuthorityCheck.class);
        }
        log.info("needAuthorityCheck:{}",needAuthorityCheck);
        //2.有则该接口需要判断是否已登录；若未登录则进入登录页
        if (needAuthorityCheck) {
            UserInfo userInfo = userInfoService.getUserInfo((String) request.getSession().getAttribute("userInfo"));
            log.info("UserInfo:{}",userInfo);
            if (userInfo == null) {
                log.info("redirect To: {}", appServerHostUrl + "/login");
                ModelAndView mv = new ModelAndView();
                mv.setViewName("redirect:" + appServerHostUrl + "/login");
                return mv;
            }
        }
        //3.没有则继续往下执行
        return point.proceed(point.getArgs());
    }
}