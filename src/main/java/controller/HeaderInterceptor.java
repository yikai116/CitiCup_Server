package controller;

/**
 * Created by Jaho on 2017/5/24.
 */


import dto.response.Response;
import entity.User;
import dao.UserMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class HeaderInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        // Set Request and Response
        httpServletRequest.setCharacterEncoding("utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers","content-type,token");


        String token = httpServletRequest.getHeader("token");

        // Header中缺少Token
        if(token == null){
            PrintWriter out = httpServletResponse.getWriter();
            out.print(new Gson().toJson(Response.NO_TOKEN_ERROR));
            return false;
        }

        // 根据Token查询对应用户
        User user = userMapper.findByToken(token);
        // 用户不存在则返回错误信息
        if(user == null){
            PrintWriter out = httpServletResponse.getWriter();
            out.print(new Gson().toJson(Response.INVALIED_TOKEN_ERROR));
            return false;
        }

        // 有效则验证通过,并将用户信息与Request绑定
        httpServletRequest.setAttribute("phone", user.getPhone());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}