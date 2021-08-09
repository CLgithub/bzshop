package com.cl.frontend.portal.controller;

import com.cl.frontend.portal.service.ItemService;
import com.cl.gzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author l
 * @Date 2021/8/7 20:41
 */
@RestController
@RequestMapping("/frontend/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 根据商品ID查询商品
     * @return
     */
    @RequestMapping("/selectItemInfo")
    public Result selectItemInfo(@RequestParam Long itemId){
        try {

            return itemService.selectItemInfo(itemId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 根据商品ID，查询商品描述
     * @param itemId
     * @return
     */
    @RequestMapping("/selectItemDescByItemId")
    public Result selectItemDescByItemId(@RequestParam Long itemId){
        try {
            return itemService.selectItemDescByItemId(itemId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
