package com.badistricia.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author badistricia
 * @version 1.0
 * @description :
 * @date 2022/6/14 9:59:09
 */
public class WebUtils {

    public static String renderString (HttpServletResponse response,String string)
    {
    try{
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(string);
    }catch (IOException e){
        e.printStackTrace();
    }
    return null;
    }
}
