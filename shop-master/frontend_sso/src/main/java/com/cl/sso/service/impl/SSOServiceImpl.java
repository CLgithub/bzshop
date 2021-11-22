package com.cl.sso.service.impl;

import com.cl.gzshop.utils.MD5Utils;
import com.cl.gzshop.utils.Result;
import com.cl.mapper.TbUserMapper;
import com.cl.pojo.TbUser;
import com.cl.pojo.TbUserExample;
import com.cl.sso.service.SSOService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
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
    public Result userLogin(String username, String password) {
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
        return Result.ok(map);
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
