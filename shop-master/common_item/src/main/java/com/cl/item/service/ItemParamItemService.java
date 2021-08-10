package com.cl.item.service;

import com.cl.pojo.TbItemParamItem;


/**
 * 商品规格参数
 * @Author l
 * @Date 2021/3/4 22:36
 */
public interface  ItemParamItemService {

    /**
     * 添加商品规格参数
     * @param tbItemParamItem
     * @return
     */
    Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem);

    /**
     * 更新商品规格参数
     * @param tbItemParamItem
     * @return
     */
    Integer updateTbItemParamItem(TbItemParamItem tbItemParamItem);

    /**
     * 根据商品id
     * @param itemId
     * @return
     */
    TbItemParamItem selectTbItemParamItemByItemId(Long itemId);
}
