package com.zhu.sm.Interceptor;

import com.zhu.sm.common.util.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/5 17:06
 * @className: LoginIntercept
 * @description:
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //放行所有的 OPTIONS 请求
        if("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        //判断用户是否登录
        return tokenService.checkToken(request);

    }
}
