package com.cl.content.service;

import com.cl.gzshop.utils.PageResult;
import com.cl.pojo.TbContent;

import java.util.List;

/**
 * @Author l
 * @Date 2021/6/4 16:31
 */
public interface ContentService {

    /**
     * 根据商品内容分类id，查询商品内容
     * @param page
     * @param rows
     * @param categoryId
     * @return
     */
    PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

    /**
     * 根据分类添加内容
     * @param tbContent
     * @return
     */
    Integer insertTbContent(TbContent tbContent);

    /**
     * 根据内容分类内容id，删除内容分类内容
     * @param ids
     * @return
     */
    Integer deleteContentByIds(Long ids);
}
