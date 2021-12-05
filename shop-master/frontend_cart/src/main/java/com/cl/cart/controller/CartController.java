package com.cl.cart.controller;

import com.cl.cart.service.CookieCartService;
import com.cl.cart.service.RedisCartService;
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
public class CartController {

    @Autowired
    private CookieCartService cookieCartService;
    @Autowired
    private RedisCartService redisCartService;

    /**
     * 添加商品到购物车🛒
     * @param itemId
     * @param userId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addItem")
    public Result addItem(@RequestParam Long itemId, @RequestParam String userId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        try {
            if(StringUtils.isBlank(userId)){ //如果userId为空，即未登录
                return cookieCartService.addItem(itemId, num, request, response);
            }else { // 在登录专题下，向购物车添加商品
                return redisCartService.addItem(itemId, num, userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 查看购物车
     * @param userId
     * @return
     */
    @RequestMapping("/showCart")
    public Result showCart(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response){
        try {
            if(StringUtils.isBlank(userId)){ //如果userId为空，即未登录
                return cookieCartService.showCart(request, response);
            }else {
                return redisCartService.showCart(userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 修改购物车中商品数量
     * @param itemId
     * @param userId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateItemNum")
    public Result updateItemNum(@RequestParam Long itemId, @RequestParam String userId, @RequestParam Integer num, HttpServletRequest request, HttpServletResponse response){
        try {
            if(StringUtils.isBlank(userId)){ //如果userId为空，即未登录
                return cookieCartService.updateItemNum(itemId, num, request, response);
            }else {
                return redisCartService.updateItemNum(itemId, num, userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 删除购物车中的商品
     * @param itemId
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteItemFromCart")
    public Result deleteItemFromCart(@RequestParam Long itemId, @RequestParam String userId, HttpServletRequest request, HttpServletResponse response){
        try {
            if(StringUtils.isBlank(userId)){ //如果userId为空，即未登录
                return cookieCartService.deleteItemFromCart(itemId, request, response);
            }else {
                return redisCartService.deleteItemFromCart(itemId, userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

    /**
     * 去结算
     * @param ids
     * @param userId
     * @return
     */
    @RequestMapping("/goSettlement")
    public Result goSettlement(@RequestParam String[] ids,@RequestParam String userId){
        try {
            return redisCartService.goSettlement(ids, userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }

}
