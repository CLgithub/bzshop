package com.cl.item.controller;

import com.cl.item.service.ItemDescService;
import com.cl.pojo.TbItemDesc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品描述
 * @Author l
 * @Date 2021/2/15 11:57
 */
@RestController
@RequestMapping("/service/itemDesc")
public class ItemDescController {

    @Resource
    ItemDescService itemDescService;

    /**
     * 添加商品描述
     */
    @RequestMapping("/insertItemDesc")
    public Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc){
        return this.itemDescService.insertItemDesc(tbItemDesc);
    }

    /**
     * 更新商品描述
     * @return
     */
    @RequestMapping("/updateItemDesc")
    public Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc){
        return this.itemDescService.updateItemDesc(tbItemDesc);
    }
}
