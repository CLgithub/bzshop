package com.cl.backend.item.service;

import com.cl.gzshop.utils.Result;

/**
 * @Author l
 * @Date 2021/2/14 15:01
 */
public interface ItemParamService {

    /**
     * 根据商品id，查询商品规格参数
     * @param itemCatId
     * @return
     */
    Result selectItemParamByItemCatId(Long itemCatId);

    /**
     * 查询所有商品规格参数
     * @param page
     * @param rows
     * @return
     */
    Result selectItemParamAll(Integer page, Integer rows);

    /**
     * 根据商品分类添加商品规格参数
     * @param itemCatId
     * @param paramData
     * @return
     */
    Result insertItemParam(Long itemCatId, String paramData);

    /**
     * 根据id删除商品规格参数
     * @param id
     * @return
     */
    Result deleteItemParamById(long id);
}
