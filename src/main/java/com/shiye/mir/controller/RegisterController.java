package com.shiye.mir.controller;

import com.shiye.mir.entity.ApiResponse;
import com.shiye.mir.entity.dto.UserInfo;
import com.shiye.mir.enums.EnumEmailSendStatus;
import com.shiye.mir.enums.EnumResponseCode;
import com.shiye.mir.service.MailService;
import com.shiye.mir.service.RegisterService;
import com.shiye.mir.service.UserInfoService;
import com.shiye.mir.utils.CommonUtils;
import com.shiye.mir.utils.LocalMapCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @Resource
    private MailService mailService;


    /**
     * 提交注册验证表单
     */
    @RequestMapping("/submit")
    @ResponseBody
    public ApiResponse<Object> register(UserInfo user){
        if(userInfoService.getUserInfo(user.getUserId())!=null){
            return ApiResponse.of("R00403",EnumResponseCode.USER_EXIST);
        }
        if(!CommonUtils.checkEmail(user.getEmail())){
            return ApiResponse.of("R00401",EnumResponseCode.EMAIL_FAILED);
        }
        if(userInfoService.getUserInfoByEmail(user.getEmail())!=null){
            return ApiResponse.of("R00405",EnumResponseCode.EMAIL_EXIST);
        }
        if(registerService.insert(user) > 0){
            if(registerService.sendVerifyMail(user.getEmail(), user.getUserId()) == EnumEmailSendStatus.EMAIL_FAILED){
                registerService.emailFailedLog(user.getUserId(), user.getEmail(),"1");
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

    /**
     * 前往密码找回页面
     */
    @RequestMapping("/forget")
    public String forget(){
        return "forget.html";
    }

    /**
     * 前往密码找回页面
     */
    @ResponseBody
    @RequestMapping(value = "/retrieve",method = RequestMethod.POST)
    public ModelAndView retrieve(String email){
        ModelAndView mv = new ModelAndView();
        UserInfo user = userInfoService.getUserInfoByEmail(email);
        if(user==null){
            mv.setViewName("retrieve_wrong.html");
        }else{
            EnumEmailSendStatus emailSendStatus = mailService.sendEmailVerifyCode(email);
            if(EnumEmailSendStatus.EMAIL_SENT.equals(emailSendStatus)){
                registerService.emailFailedLog(user.getUserId(), email,"2");
            }
            mv.setViewName("retrieve.html");
            mv.addObject("email",user.getEmail());
        }
        return mv;
    }

    /**
     * 确认修改密码
     */
    @ResponseBody
    @RequestMapping("/change")
    public ApiResponse<Object> change(String email, String newPassword, String newPassword2, String verifyCode){
        //缺少输入
        if(newPassword.isEmpty() || newPassword2.isEmpty() || verifyCode.isEmpty()){
            return ApiResponse.of("P00402",EnumResponseCode.INPUT_CHECK);
        }
        System.out.println("11:"+email);
        System.out.println("22:"+LocalMapCache.getEmailCache().get(email));
        //验证码不对
        if(!verifyCode.equals(LocalMapCache.getEmailCache().get(email))){
            return ApiResponse.of("P00401",EnumResponseCode.WRONG_VERIFY_2);
        }

        //两次输入不一致
        if(!newPassword.equals(newPassword2)){
            return ApiResponse.of("P00400",EnumResponseCode.PASSWORD_DIF);
        }
        if(userInfoService.changePassword(email,newPassword)>0){
            return ApiResponse.of("A00000",EnumResponseCode.SUCCESS);
        }
        return ApiResponse.of("R00400",EnumResponseCode.REGISTER_FAILED);
    }

}
