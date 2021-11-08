package com.cl.cart.service;

import com.cl.gzshop.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; /**
 * @Author l
 * @Date 2021/8/11 15:35
 */
public interface CookieCarService {

    /**
     * 在未登录的情况下，添加商品到购物车🛒
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    Result addItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

    /**
     * 在未登录的状态下，查看购物车
     * @param request
     * @param response
     * @return
     */
    Result showCart(HttpServletRequest request, HttpServletResponse response);

    /**
     * 修改购物车中商品数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    Result updateItemNum(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

    /**
     * 在未登录的状态下，删除购物车中的商品
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    Result deleteItemFromCart(Long itemId, HttpServletRequest request, HttpServletResponse response);
}
