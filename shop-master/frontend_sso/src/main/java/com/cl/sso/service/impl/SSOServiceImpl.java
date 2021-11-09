package com.cl.sso.service.impl;

import com.cl.gzshop.utils.Result;
import com.cl.mapper.TbUserMapper;
import com.cl.pojo.TbUserExample;
import com.cl.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
