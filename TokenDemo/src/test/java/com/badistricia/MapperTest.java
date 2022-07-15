package com.badistricia;

import com.badistricia.user.mapper.SysMenuMapper;
import com.badistricia.user.mapper.SysUserMapper;
import com.badistricia.user.model.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author badistricia
 * @version 1.0
 * @description :
 * @date 2022/6/14 15:15:12
 */

@SpringBootTest
public class MapperTest {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;
    //也可以直接用这个来match，encode
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void TestBCryptPasswordEncoder(){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode1 = encoder.encode("123456");
//        String encode2 = encoder.encode("123456");
//        //发现两次加密都不一样
        if (encoder.matches("123456","$2a$10$4JtLJ9t.SeOB5GYNoB9Z4.7H0DSb/VlCrjql2hY0deTI0LJ9Kh8S6")) {
            System.out.println(true);
        }else
            System.out.println(false);
//        System.out.println(encode1);
//        System.out.println(encode2);

    }
@Test
    public void test (){
    LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(SysUser::getUserName,"badistricia");
    System.out.println(userMapper.selectOne(wrapper));
}


@Test
    public void menutest(){
    System.out.println(sysMenuMapper.selectPermsByUserId(2L));
}
}
