package com.nanting.point.nantingdianping.controller;

import com.nanting.point.nantingdianping.dto.LoginFormDTO;
import com.nanting.point.nantingdianping.dto.Result;
import com.nanting.point.nantingdianping.service.userService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private userService userService;

    /**
     * 发送手机验证码
     */
    @PostMapping("code")
    public Result sendCode(String phone, HttpSession session){
        log.info("发送验证码");
        return userService.sendCode(phone,session);
    }

    /**
     * 登录功能
     * @param loginForm 登录参数，包含手机号、验证码；或者手机号、密码
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginForm, HttpSession session){
        // 实现登录功能
        return userService.login(loginForm, session);
    }

}
