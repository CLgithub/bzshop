package com.cl.content.service;

import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbContent;

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

    /**
     * 根据分类添加内容
     * @param tbContent
     * @return
     */
    Result insertTbContent(TbContent tbContent);

    /**
     * 根据id，删除内容
     * @param ids
     * @return
     */
    Result deleteContentByIds(Long ids);
}
