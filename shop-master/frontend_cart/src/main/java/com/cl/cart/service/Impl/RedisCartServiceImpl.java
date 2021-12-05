package com.cl.cart.service.Impl;

import com.cl.cart.feign.CloudCommonItemFeignClient;
import com.cl.cart.service.CookieCartService;
import com.cl.cart.service.RedisCartService;
import com.cl.gzshop.utils.CartItem;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author l
 * @Date 2021/11/14 21:00
 */
@Service
public class RedisCartServiceImpl implements RedisCartService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CloudCommonItemFeignClient cloudCommonItemFeignClient;
    @Autowired
    private CookieCartService cookieCartService;
    @Value("${frontend_cart_redis_key}")
    private String frontend_cart_redis_key;

    @Override
    public Result addItem(Long itemId, Integer num, String userId) {
        // 1 获取用户购物车
        Map<String, CartItem> cartItemMap = this.getCart(userId);
        Set<String> strings = cartItemMap.keySet();

        // 2 查询商品
        TbItem tbItem = cloudCommonItemFeignClient.selectItemInfo(itemId);
        // 3 向购物车中添加商品
        cookieCartService.additemToCart(cartItemMap, tbItem, num);
        // 4 将购物车保存在redis中
        redisTemplate.opsForHash().put(frontend_cart_redis_key,userId,cartItemMap);
        return Result.ok();
    }


    /**
     * 根据用户ID, userId查询redis中的用户购物车
     * @param userId
     * @return
     */
    private Map<String, CartItem> getCart(String userId) {
        try {
            Map<String, CartItem> map= (Map<String, CartItem>) redisTemplate.opsForHash().get(frontend_cart_redis_key, userId);
            if(map==null) return new HashMap<>();
            return map;
        } catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<String, CartItem>();
    }


    @Override
    public Result showCart(String userId) {
        List<CartItem> list=new ArrayList<>();
        Map<String, CartItem> cart = getCart(userId);
        Set<String> keys= cart.keySet();
        for(String key:keys){
            list.add(cart.get(key));
        }
        return Result.ok(list);
    }

    @Override
    public Result updateItemNum(Long itemId, Integer num, String userId) {
        Map<String, CartItem> cart = getCart(userId);
        CartItem cartItem = cart.get(itemId.toString());
        if(cartItem != null) cartItem.setNum(num);
        redisTemplate.opsForHash().put(frontend_cart_redis_key,userId,cart);
        return Result.ok();
    }

    @Override
    public Result deleteItemFromCart(Long itemId, String userId) {
        Map<String, CartItem> cartItemMap = getCart(userId);
        CartItem remove = cartItemMap.remove(itemId.toString());
        redisTemplate.opsForHash().put(frontend_cart_redis_key,userId,cartItemMap);
        return Result.ok();
    }

    @Override
    public Result goSettlement(String[] ids, String userId) {
        // 获取购物车
        Map<String, CartItem> cart = getCart(userId);
        List<CartItem> list=new ArrayList<>();
        // 从购物车中获取选中的商品
        for(String itemId: ids){
            CartItem cartItem = cart.get(itemId);
            list.add(cartItem);
        }
        return Result.ok(list);
    }


}
