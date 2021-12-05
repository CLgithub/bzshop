package com.cl.cart.interceptor;

import com.cl.cart.service.UserCheckService;
import com.cl.pojo.TbUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author l
 * @Date 2021/12/5 15:58
 */

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserCheckService userCheckService;

    /**
     * 前置拦截，返回true放行请求，返回false拦截请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");   // 如果用户token为空，则进行拦截
        if(StringUtils.isBlank(token)) {
            return false;
        }else{  // 如果用户token不为空
            TbUser tbUser = userCheckService.checkUserToken(token); // 判断token是否失效
            if(tbUser==null){
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
