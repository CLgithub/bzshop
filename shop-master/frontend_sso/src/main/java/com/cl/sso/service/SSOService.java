package com.cl.sso.service;

import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author l
 * @Date 2021/11/9 23:31
 */
public interface SSOService {

    /**
     * 对用户的注册信息（用户名和电话号码）做数据校验
     * @param checkValue 校验的值
     * @param checkFlag 1：校验用户名是否存在，2：校验手机号是否存在
     * @return
     */
    Result checkUserInfo(String checkValue, int checkFlag);

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    Result userRegister(TbUser tbUser);

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);


    /**
     * 用户登出
     * @param token
     * @return
     */
    Result logOut(String token);
}
