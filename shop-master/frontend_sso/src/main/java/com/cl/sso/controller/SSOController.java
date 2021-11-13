package com.cl.sso.controller;

import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbUser;
import com.cl.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册于登录
 * @Author l
 * @Date 2021/11/9 23:26
 */
@RestController
@RequestMapping("/frontend_sso/sso")
public class SSOController {

    @Autowired
    private SSOService ssoService;

    /**
     * 对用户的注册信息（用户名和电话号码）做数据校验
     * @param checkValue 校验的值
     * @param checkFlag 1：校验用户名是否存在，2：校验手机号是否存在
     * @return
     */
    @RequestMapping("checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable String checkValue, @PathVariable int checkFlag){
        try {
            return ssoService.checkUserInfo(checkValue, checkFlag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }


    /**
     * 完成用户注册
     * @param tbUser
     * @return
     */
    @RequestMapping("userRegister")
    public Result userRegister(TbUser tbUser){
        try {
            return ssoService.userRegister(tbUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
