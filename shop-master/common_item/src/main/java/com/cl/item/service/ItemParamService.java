package com.cl.item.service;

import com.cl.gzshop.utils.PageResult;
import com.cl.pojo.TbItemParam;

/**
 * @Author l
 * @Date 2021/2/14 14:25
 */
public interface ItemParamService {

    /**
     * 根据商品id，查询商品规格参数
     * @param itemCatId
     * @return
     */
    TbItemParam selectItemParamByItemCatId(Long itemCatId);

    /**
     * 查询所有商品规格参数
     * @param page
     * @param rows
     * @return
     */
    PageResult selectItemParamAll(Integer page, Integer rows);

    /**
     * 根据商品分类添加商品规格参数
     * @param tbItemParam
     * @return
     */
    Integer insertItemParam(TbItemParam tbItemParam);

    /**
     * 根据id删除商品规格参数
     * @param id
     * @return
     */
    Integer deleteItemParamById(long id);
}
