package com.badistricia.user.service.impl;

import com.badistricia.user.model.SysUser;
import com.badistricia.user.mapper.SysUserMapper;
import com.badistricia.user.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author badistricia
 * @since 2022-06-14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
