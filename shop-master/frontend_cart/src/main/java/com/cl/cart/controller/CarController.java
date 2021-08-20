package com.cl.cart.controller;

import com.cl.cart.service.CookieCarService;
import com.cl.gzshop.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author l
 * @Date 2021/8/11 15:22
 */
@RestController
@RequestMapping("/frontend_cart/cart")
public class CarController {

    @Autowired
    private CookieCarService cookieCarService;

    @RequestMapping("/addItem")
    public Result addItem(@RequestParam Long itemId, @RequestParam String userId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        try {
            if(StringUtils.isBlank(userId)){ //如果userId为空，即未登录
                return cookieCarService.addItem(itemId, num, request, response);
            }else {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("加入有误");
    }
}
