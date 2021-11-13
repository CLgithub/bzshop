package com.cl.sso.service.impl;

import com.cl.gzshop.utils.MD5Utils;
import com.cl.gzshop.utils.Result;
import com.cl.mapper.TbUserMapper;
import com.cl.pojo.TbUser;
import com.cl.pojo.TbUserExample;
import com.cl.sso.service.SSOService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户注册于登录业务层
 * @Author l
 * @Date 2021/11/9 23:31
 */
@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private TbUserMapper tbUserMapper;


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


}
