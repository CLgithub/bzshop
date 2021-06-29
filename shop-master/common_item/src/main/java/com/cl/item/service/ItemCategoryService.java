package com.cl.item.service;

import com.cl.gzshop.utils.CatResult;
import com.cl.pojo.TbItemCat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author l
 * @Date 2020/9/23 19:39
 */
public interface ItemCategoryService {

    /**
     * 根据分类parentId查询子节点
     * @param parentId
     * @return
     */
    List<TbItemCat> selectItemCategoryByParentId(Long parentId);

    /**
     * 查询首页商品分类
     * @return
     */
    CatResult selectItemCategoryAll();
}
