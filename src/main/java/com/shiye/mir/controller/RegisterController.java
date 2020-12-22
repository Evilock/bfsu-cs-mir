package com.shiye.mir.controller;

import com.shiye.mir.entity.ApiResponse;
import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.enums.EnumEmailSendStatus;
import com.shiye.mir.enums.EnumResponseCode;
import com.shiye.mir.service.RegisterService;
import com.shiye.mir.service.UserInfoService;
import com.shiye.mir.utils.CheckAuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 注册页
 * @author fangshaozu
 */
@Controller
@RequestMapping(value = "/register",produces = "application/json;charset=UTF-8")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @Resource
    private UserInfoService userInfoService;

    /**
     * 提交注册验证表单
     */
    @RequestMapping("/submit")
    @ResponseBody
    public ApiResponse<Object> register(UserInfo user){
        if(userInfoService.getUserInfo(user.getUserId())!=null){
            return ApiResponse.of("R00403",EnumResponseCode.USER_EXIST);
        }
        if(!CheckAuthorityUtils.checkEmail(user.getEmail())){
            return ApiResponse.of("R00401",EnumResponseCode.EMAIL_FAILED);
        }
        if(userInfoService.getUserInfoByEmail(user.getEmail())!=null){
            return ApiResponse.of("R00405",EnumResponseCode.EMAIL_EXIST);
        }
        if(registerService.insert(user) > 0){
            if(registerService.sendVerifyMail(user.getEmail(), user.getUserId()) == EnumEmailSendStatus.EMAIL_FAILED){
                registerService.emailFailedLog(user.getUserId(), user.getEmail());
            }
            return ApiResponse.of("R00200",EnumResponseCode.REGISTER_SUCCESS);
        }
        return ApiResponse.of("R00400",EnumResponseCode.REGISTER_FAILED);
    }

    /**
     * 完成邮箱认证
     * @param id 用户ID
     */
    @RequestMapping("/verify")
    public String register(String id){
        if(registerService.activate(id)>0){
            return "verify.html";
        }else{
            return "verify_failed.html";
        }
    }
}
