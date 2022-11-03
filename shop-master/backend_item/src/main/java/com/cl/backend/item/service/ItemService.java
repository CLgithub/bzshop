package com.cl.backend.item.service;

import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author l
 * @Date 2020/9/23 17:08
 */
public interface ItemService {

    /**
     * 查询商品并分页
     * @param page
     * @param rows
     * @return
     */
    Result selectTbItemAllByPage(Integer page, Integer rows);

    /**
     * 添加商品
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    Result insertTbItem(TbItem tbItem, String desc, String itemParams);

    /**
     * 根据id，删除商品
     * @param itemId
     * @return
     */
    Result deleteItemById(Long itemId);

    /**
     * 预更新商品查询
     * @param itemId
     * @return
     */
    Result preUpdateItem(Long itemId);

    /**
     * 更新
     * @param tbItem
     * @param desc
     *@param itemParams @return
     */
    Result updateTbItem(TbItem tbItem, String desc, String itemParams);
}
