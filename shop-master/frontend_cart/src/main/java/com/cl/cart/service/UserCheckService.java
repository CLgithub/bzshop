package com.cl.cart.service;

import com.cl.pojo.TbUser;

/**
 * @Author l
 * @Date 2021/12/5 16:06
 */
public interface UserCheckService {

    /**
     * 检测用户token是否有效
     * @param token
     * @return
     */
    TbUser checkUserToken(String token);
}
