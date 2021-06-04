package com.cl.backend.item.service;

import com.cl.gzshop.utils.Result;

/**
 * @Author l
 * @Date 2020/9/24 09:43
 */
public interface ItemCategoryService {
    /**
     * 根据分类parentId查询子节点
     * @param id
     * @return
     */
    Result selectItemCategoryByParentId(Long id);
}
