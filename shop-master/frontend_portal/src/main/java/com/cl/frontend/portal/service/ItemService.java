package com.cl.frontend.portal.service;

import com.cl.gzshop.utils.Result;

/**
 * @Author l
 * @Date 2021/8/7 20:45
 */
public interface ItemService {

    /**
     * 根据商品ID查询商品
     * @param itemId
     * @return
     */
    Result selectItemInfo(Long itemId);

    /**
     * 根据商品ID，查询商品描述
     * @param itemId
     * @return
     */
    Result selectItemDescByItemId(Long itemId);
}
