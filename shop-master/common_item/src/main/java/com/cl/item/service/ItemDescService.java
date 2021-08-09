package com.cl.item.service;

import com.cl.pojo.TbItemDesc;

/**
 * 商品描述
 * @Author l
 * @Date 2021/3/4 22:22
 */
public interface ItemDescService {

    /**
     * 添加商品描述
     * @param tbItemDesc
     * @return
     */
    Integer insertItemDesc(TbItemDesc tbItemDesc);

    /**
     * 更新商品描述
     * @param tbItemDesc
     * @return
     */
    Integer updateItemDesc(TbItemDesc tbItemDesc);

    /**
     * 根据商品ID，查询商品描述
     * @param itemId
     * @return
     */
    TbItemDesc selectItemDescByItemId(Long itemId);
}
