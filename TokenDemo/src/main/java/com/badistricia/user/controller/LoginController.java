package com.badistricia.user.controller;

import com.badistricia.api.ResponseResult;
import com.badistricia.user.model.SysUser;
import com.badistricia.user.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author badistricia
 * @version 1.0
 * @description :
 * @date 2022/6/14 20:18:35
 */
@RestController
public class LoginController {


    @Autowired
    LoginService service;
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody SysUser user){
        //登录
        return service.login(user);

    }
    @PostMapping("/user/logout")
    public ResponseResult logout(){
        //登c出
        return service.loginout();

    }

}
