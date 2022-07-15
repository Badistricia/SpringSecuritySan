package com.badistricia.user.service;

import com.badistricia.api.ResponseResult;
import com.badistricia.user.model.LoginUser;
import com.badistricia.user.model.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * @author badistricia
 * @version 1.0
 * @description :
 * @date 2022/6/14 20:25:19
 */
@Service
public interface LoginService{
    ResponseResult login(SysUser user);

    ResponseResult loginout();
}
