package com.cl.cart.service.Impl;

import com.cl.cart.feign.CloudCommonItemFeignClient;
import com.cl.cart.service.CookieCarService;
import com.cl.gzshop.utils.CartItem;
import com.cl.gzshop.utils.CookieUtils;
import com.cl.gzshop.utils.JsonUtils;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author l
 * @Date 2021/8/11 15:36
 */
@Service
public class CookieCarServiceImpl implements CookieCarService {

    @Autowired
    private CloudCommonItemFeignClient cloudCommonItemFeignClient;

    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    @Override
    public Result addItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        // 1 获取临时购物车
        Map<String, CartItem> cartItemMap = this.getCart(request);
        // 2 查询商品
        TbItem tbItem = cloudCommonItemFeignClient.selectItemInfo(itemId);
        // 3 向购物车中添加商品
        this.additemToCart(cartItemMap, tbItem, num, itemId);
        // 4 将购物车通过Cookie写回给客户端浏览器
        addClientCookie(request, response, cartItemMap);
        return Result.ok();
    }

    /**
     * 4 将购物车通过Cookie写回给客户端浏览器
     * @param request
     * @param response
     * @param cartItemMap
     */
    private void addClientCookie(HttpServletRequest request, HttpServletResponse response, Map<String, CartItem> cartItemMap) {
        String s = JsonUtils.objectToJson(cartItemMap);
        CookieUtils.setCookie(request, response, this.cart_cookie_name, s, true);
    }

    /**
     * 3 将商品添加到购物车中
     * @param cartItemMap  购物车
     * @param tbItem
     * @param num 数量
     * @param itemId
     */
    private void additemToCart(Map<String, CartItem> cartItemMap, TbItem tbItem, Integer num, Long itemId) {
        // 从购物车中取商品
        CartItem cartItem = cartItemMap.get(itemId.toString());
        if(cartItem == null){
            CartItem cartItem1 = null;
            cartItem1 = this.getCartItem(tbItem);
            cartItemMap.put(itemId.toString(), cartItem1);
        }else{
            cartItem.setNum(cartItem.getNum()+num);
        }
    }

    /**
     * 根据商品，创建购物车商品模型
     * @param tbitem
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public CartItem getCartItem(TbItem tbitem) {
        try {
            CartItem cartItem = new CartItem();
            Class<CartItem> cartItemClass = CartItem.class;
            Class<TbItem> tbItemClass = TbItem.class;
            Field[] declaredFields = cartItemClass.getDeclaredFields();
            for(Field field:declaredFields){
                String name = field.getName();
                Method getMethod= null;
                    getMethod = tbItemClass.getDeclaredMethod("get" + StringUtils.capitalize(name), null);
                Method setMethod= cartItemClass.getDeclaredMethod("set" + StringUtils.capitalize(name), field.getType());
                Object invoke1 = setMethod.invoke(cartItem, getMethod.invoke(tbitem));
            }
            return cartItem;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 1 获取购物车
     * @param request
     * @return
     */
    private Map<String,CartItem> getCart(HttpServletRequest request) {
        String cartJson= CookieUtils.getCookieValue(request, cart_cookie_name, true);
        if(StringUtils.isBlank(cartJson)){ // 2 临时购物车不存在
            return new HashMap<>();
        }
        try{
            // 需要做json转换
            return JsonUtils.jsonToMap(cartJson, CartItem.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }


}
