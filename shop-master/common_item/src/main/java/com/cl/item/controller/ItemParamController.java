package com.cl.item.controller;

import com.cl.gzshop.utils.PageResult;
import com.cl.item.service.ItemParamService;
import com.cl.pojo.TbItemParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 规格参数模版
 * @Author l
 * @Date 2021/2/14 14:18
 */
@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {

    @Resource
    ItemParamService itemParamService;
    /**
     *  根据商品分类ID，查询规格参数模版
     */
    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(@RequestParam Long itemCatId){
        return itemParamService.selectItemParamByItemCatId(itemCatId);
    }

    /**
     * 查询所有商品规格参数模版
     */
    @RequestMapping("/selectItemParamAll")
    public PageResult selectItemParamAll(@RequestParam Integer page, @RequestParam Integer rows){
        return itemParamService.selectItemParamAll(page,rows);
    }

    /**
     * 根据商品分类添加商品规格参数
     */
    @RequestMapping("/insertItemParam")
    public Integer insertItemParam(@RequestBody TbItemParam tbItemParam){
         return itemParamService.insertItemParam(tbItemParam);
    }

    /**
     * 根据id删除商品规格参数
     * @param id
     * @return
     */
    @RequestMapping("/deleteItemParamById")
    public Integer deleteItemParamById(@RequestParam long id){
        return itemParamService.deleteItemParamById(id);
    }
}
