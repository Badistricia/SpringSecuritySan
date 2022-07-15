package com.badistricia.user.service.impl;

import com.badistricia.user.mapper.SysMenuMapper;
import com.badistricia.user.mapper.SysUserMapper;
import com.badistricia.user.model.LoginUser;
import com.badistricia.user.model.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author badistricia
 * @version 1.0
 * @description :
 * @date 2022/6/14 15:44:19
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysMenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName,username);
        SysUser user = userMapper.selectOne(wrapper);
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //TODO 查询对应的权限信息,先写死
        //利用有参构造直接注入初始test以及admin信息
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("test","admin"));

        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        //把数据封装成UserDetail返回,通过有参构造函数注入查询到的user
        //因为最后返回的是LoginUser类型的，也就是自己重写的UserDetail类型，所以SpringSecurity会使用这个对象来进行后续操作
        return new LoginUser(user,list);
    }
}
