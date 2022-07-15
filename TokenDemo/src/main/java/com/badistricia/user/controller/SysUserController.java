package com.badistricia.user.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author badistricia
 * @since 2022-06-14
 */
@RestController
public class SysUserController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:test:list')")
    public String hello(){
        return "hello";
    }
}

