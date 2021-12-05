package com.cl.sso.service.impl;

import com.cl.gzshop.utils.*;
import com.cl.mapper.TbUserMapper;
import com.cl.pojo.TbUser;
import com.cl.pojo.TbUserExample;
import com.cl.sso.service.SSOService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 用户注册于登录业务层
 * @Author l
 * @Date 2021/11/9 23:31
 */
@Service
@CacheConfig(cacheNames = "frontend:SSO:redis")
public class SSOServiceImpl implements SSOService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private SSOService ssoService;

    @Value("${cart_cookie_name}")
    private String cart_cookie_name;
    @Value("${frontend_cart_redis_key}")
    private String frontend_cart_redis_key;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Result checkUserInfo(String checkValue, int checkFlag) {
        TbUserExample example=new TbUserExample();
        TbUserExample.Criteria criteria=example.createCriteria();
        if(checkFlag==1){
            criteria.andUsernameEqualTo(checkValue);
        }else if(checkFlag==2){
            criteria.andPhoneEqualTo(checkValue);
        }
        Integer rows= tbUserMapper.countByExample(example);
        if(rows > 0){
            return Result.error("数据已经存在");
        }
        return Result.ok(checkValue);
    }


    @Override
    @LcnTransaction // 分布式事务
    public Result userRegister(TbUser tbUser) {
        // 将密码进行加密
        String pwd = MD5Utils.digest(tbUser.getPassword());
        tbUser.setPassword(pwd);
        // 补齐数据
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        int insert = tbUserMapper.insert(tbUser);
        if(insert>0){
            return Result.ok();
        }
        return null;
    }

    @Override
    @LcnTransaction
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        // 根据用户名密码查询数据库
        TbUser tbUser=this.login(username,password);
        if(tbUser== null){
            return Result.error("用户名或密码错误！");
        }
        String userToken= UUID.randomUUID().toString();
        ssoService.cacheLoginToken(userToken, tbUser); // 将user缓存到redis
        Map<String, String> map=new HashMap<>();
        map.put("token", userToken);
        map.put("userid", tbUser.getId().toString());
        map.put("username", tbUser.getUsername());
        // 将临时购物车中的商品同步到购物车中，并且删除临时购物车中的商品
        sysncAndDelCookieCart(tbUser.getId().toString(),request, response);
        return Result.ok(map);
    }

    /**
     * 同步临时购物车到用户购物车
     * @param userId
     * @param request
     */
    private void sysncAndDelCookieCart(String userId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, CartItem> cookieCart= getCookieCart(request); // 得到临时购物车
        Map<String, CartItem> redisCart = getRedisCart(userId); // 得到用户购物车
        // 删除用户购物车中包含有临时购物车中的商品
        for(Iterator<String> iterator = cookieCart.keySet().iterator();iterator.hasNext();){
            String key1= iterator.next();
            CartItem remove = redisCart.remove(key1);
        }
        redisCart.putAll(cookieCart);
        // 保存永久购物车
        redisTemplate.opsForHash().put(frontend_cart_redis_key,userId,redisCart);
        // 清空临时购物车中的商品并保存
//        cookieCart.clear();
//        String s = JsonUtils.objectToJson(cookieCart);
//        CookieUtils.setCookie(request, response, cart_cookie_name, s, true);
        CookieUtils.deleteCookie(request, response, cart_cookie_name);
    }

    /**
     * 获取临时购物车
     * @param request
     * @return
     */
    private Map<String, CartItem> getCookieCart(HttpServletRequest request) {
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
    /**
     * 根据用户ID, userId查询redis中的用户购物车
     * @param userId
     * @return
     */
    private Map<String, CartItem> getRedisCart(String userId) {
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
    @Cacheable(key = "#token")
    public String cacheLoginToken(String token, TbUser tbUser) {
        tbUser.setPassword("");
        return tbUser.toString();
    }

    private TbUser login(String username, String password) {
        String pwd = MD5Utils.digest(password);
        TbUserExample example=new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(pwd);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if(tbUsers == null || tbUsers.size()==0){
            return null;
        }
        return tbUsers.get(0);
    }

    @Override
    @CacheEvict(key = "#token")
    public Result logOut(String token) {
        return Result.ok();
    }

}
