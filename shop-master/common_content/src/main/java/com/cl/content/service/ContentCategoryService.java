package com.cl.content.service;

import com.cl.pojo.TbContentCategory;

import java.util.List;

/**
 * @Author l
 * @Date 2021/3/16 22:34
 */
public interface ContentCategoryService {

    /**
     * 根据parentid查询子节点
     * @param parentId
     * @return
     */
    List<TbContentCategory> selectContentCategoryByParentId(Long parentId);

    /**
     * 添加内容分类
     * @param tbContentCategory
     * @return
     */
    Integer insertContentCategory(TbContentCategory tbContentCategory);

    /**
     * 根据id，删除内容分类
     * @param categoryId
     * @return
     */
    Integer deleteContentCategoryById(Long categoryId);

    /**
     * 修改内容分类
     * @param tbContentCategory
     * @return
     */
    Integer updateContentCategory(TbContentCategory tbContentCategory);


    long long1();
}
