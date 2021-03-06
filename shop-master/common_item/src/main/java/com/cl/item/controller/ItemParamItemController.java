package com.cl.item.controller;

import com.cl.item.service.ItemParamItemService;
import com.cl.pojo.TbItemParamItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品规格参数
 * @Author l
 * @Date 2021/3/4 22:31
 */
@RestController
@RequestMapping("/service/itemParamItem")
public class ItemParamItemController {

    @Resource
    ItemParamItemService itemParamItemService;

    /**
     * 添加商品规格参数
     * @param tbItemParamItem
     * @return
     */
    @RequestMapping("/insertTbItemParamItem")
    public Integer insertTbItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        return itemParamItemService.insertTbItemParamItem(tbItemParamItem);
    }

    /**
     * 更新商品规格参数
     * @param tbItemParamItem
     * @return
     */
    @RequestMapping("/updateTbItemParamItem")
    public Integer updateTbItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        return itemParamItemService.updateTbItemParamItem(tbItemParamItem);
    }

    /**
     * 根据商品id，查询商品规格参数
     * @param itemId
     * @return
     */
    @RequestMapping("/selectTbItemParamItemByItemId")
    public TbItemParamItem selectTbItemParamItemByItemId(@RequestParam Long itemId){
        return itemParamItemService.selectTbItemParamItemByItemId(itemId);
    }

}
