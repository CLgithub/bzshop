package com.cl.common.reids.service;

import com.cl.gzshop.utils.CatResult; /**
 * @Author l
 * @Date 2021/7/1 11:26
 */
public interface ItemCategoryService {

    /**
     * 向redis中添加数缓存数据
     * @param catResult
     */
    void insertItemCategory(CatResult catResult);

    /**
     * 查询缓存数据
     * @return
     */
    CatResult selectItemCategory();
}
