package com.cl.backend.item.controller;

import com.cl.backend.item.service.ItemParamService;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItemCat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.annotation.Repeatable;
import java.util.List;

/**
 * 商品规格规格参数模版
 * @Author l
 * @Date 2021/2/14 14:55
 */
@RestController
@RequestMapping("/backend/itemParam")
public class ItemParamController {

    @Resource
    private ItemParamService itemParamService;

    /**
     * 根据商品分类id，查询规格参数模版
     * @param itemCatId
     * @return
     */
    @RequestMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId){
        try {
            return itemParamService.selectItemParamByItemCatId(itemCatId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 查询所有商品规格参数模版
     */
    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10")Integer rows){
        try {
            return itemParamService.selectItemParamAll(page, rows);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 根据商品分类添加商品规格参数
     */
    @RequestMapping("/insertItemParam")
    public Result insertItemParam(@RequestParam Long itemCatId, @RequestParam String paramData){
        try {
            return itemParamService.insertItemParam(itemCatId, paramData);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 根据id删除商品规格参数
     * @param id
     * @return
     */
    @RequestMapping("/deleteItemParamById")
    public Result deleteItemParamById(@RequestParam long id){
        try {
            return itemParamService.deleteItemParamById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
