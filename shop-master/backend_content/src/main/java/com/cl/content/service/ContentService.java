package com.cl.content.service;

import com.cl.gzshop.utils.Result;

/**
 * @Author l
 * @Date 2021/6/27 18:03
 */
public interface ContentService {

    /**
     * 根据商品内容分类id，查询商品内容
     * @param categoryId
     * @return
     */
    Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

}
