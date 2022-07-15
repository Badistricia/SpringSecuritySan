package com.badistricia.handler;

import com.alibaba.fastjson.JSON;
import com.badistricia.api.ResponseResult;
import com.badistricia.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

/**
 * @author badistricia
 * @version 1.0
 * @description :
 * @date 2022/6/15 22:55:21
 */
@Component
public class AuthencationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //处理异常
        ResponseResult responseBody = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户名认证失败请重新登陆",null);

        String json = JSON.toJSONString(responseBody);
        WebUtils.renderString(httpServletResponse,json);

    }
}
