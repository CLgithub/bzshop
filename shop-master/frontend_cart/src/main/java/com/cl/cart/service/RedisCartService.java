package com.cl.cart.service;

import com.cl.gzshop.utils.Result;

/**
 * @Author l
 * @Date 2021/11/14 20:57
 */
public interface RedisCartService {

    /**
     * 在登录的状态下，添加商品到购物车🛒
     * @param itemId
     * @param num
     * @param userId
     * @return
     */
    Result addItem(Long itemId, Integer num, String userId);

    /**
     * 在登录状态下查看购物车
     * @param userId
     * @return
     */
    Result showCart(String userId);

    /**
     * 在登录状态下，修改购物车中的商品数量
     * @param itemId
     * @param num
     * @param userId
     * @return
     */
    Result updateItemNum(Long itemId, Integer num, String userId);

    /**
     * 在登录状态下，删除购物车当中的商品
     * @param itemId
     * @param userId
     * @return
     */
    Result deleteItemFromCart(Long itemId, String userId);

    /**
     * 去结算
     * @param ids
     * @param userId
     * @return
     */
    Result goSettlement(String[] ids, String userId);
}
