package com.badistricia.user.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author badistricia
 * @version 1.0
 * @description : 接受User有参构造函数，然后把user信息赋值给自己
 * @date 2022/6/14 15:58:32
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private SysUser user;

    private List<String> permissions;

    public LoginUser(SysUser user,List<String> permissions) {
        this.permissions = permissions;
        this.user= user;
    }

    @JSONField(serialize=false)
    private List<SimpleGrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null){
            return authorities;
        }
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority
//       方法一，使用for来
//        authorities = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            newList.add(authority);
//        }
//        方法二，使用stream流，建议学习一下Steam流式编程 函数式编程

        authorities =
                permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
