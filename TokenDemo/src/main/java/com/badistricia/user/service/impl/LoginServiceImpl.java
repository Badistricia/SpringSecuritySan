package com.badistricia.user.service.impl;


import com.badistricia.api.ResponseResult;
import com.badistricia.user.model.LoginUser;
import com.badistricia.user.model.SysUser;
import com.badistricia.user.service.LoginService;

import com.badistricia.utils.JwtUtil;
import com.badistricia.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author badistricia
 * @version 1.0
 * @description :
 * @date 2022/6/14 20:26:25
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(SysUser user) {
        //进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出提示
        if(Objects.isNull(authentication)){
            throw new RuntimeException("登陆失败");
        }
        //如果认证通过，用userid生成jwt,jwt存入responsebody返回
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        //把完整信息存入redis、userid作为key,value就是loginuser
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);

        redisCache.setCacheObject("login:"+userid,loginUser);
        return new ResponseResult(200,"登陆成功",map);
    }

    @Override
    public ResponseResult loginout() {
        //获取SecurityContextHolder内信息id,过滤器获取SecurityContextHolder里面的注入的值是UsernamePasswordAuthenticationToken，这里强转一下
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();

        Long userid = loginUser.getUser().getId();


        //删除redis中的值
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"注销成功",null);
    }
}
