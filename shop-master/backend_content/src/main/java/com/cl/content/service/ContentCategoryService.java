package com.cl.content.service;

import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbContentCategory;

import java.util.List;

/**
 * @Author l
 * @Date 2021/3/16 22:48
 */
public interface ContentCategoryService {

    /**
     * 根据parentid查询子节点
     * @param id
     * @return
     */
    Result selectContentCategoryByParentId(Long id);

    /**
     * 添加内容分类
     * @param name
     * @param parentId
     * @return
     */
    Result insertContentCategory(String name, Long parentId);

    /**
     *
     * @param categoryId
     * @return
     */
    Result deleteContentCategoryById(Long categoryId);

    /**
     * 修改内容分类名称
     * @param categoryId
     * @param name
     * @return
     */
    Result updateContentCategory(TbContentCategory tbContentCategory);


}
