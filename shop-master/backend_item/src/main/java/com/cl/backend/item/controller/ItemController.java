package com.cl.backend.item.controller;

import com.cl.backend.item.service.ItemService;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItem;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author l
 * @Date 2020/9/23 17:01
 */
@RestController
@RequestMapping("/backend/item")
public class ItemController {

    @Resource
    private ItemService itemService;

    /**
     * 查询商品并分页处理
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectTbItemAllByPage1")
    public Object selectTbItemAllByPage1(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows ){

        try {
            return itemService.selectTbItemAllByPage1(page, rows);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询商品并分页处理
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows){

        try {
            return itemService.selectTbItemAllByPage(page, rows);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 插入商品
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams){
        try {
            return itemService.insertTbItem(tbItem, desc, itemParams);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 删除商品
     * @param itemId
     * @return
     */
    @RequestMapping("/deleteItemById")
    public Result deleteItemById(@RequestParam Long itemId){
        try {
            return itemService.deleteItemById(itemId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 预更新商品查询
     * @param itemId
     * @return
     */
    @RequestMapping("/preUpdateItem")
    public Result preUpdateItem(@RequestParam Long itemId){
        try {
            return itemService.preUpdateItem(itemId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 更新商品
     */
    @RequestMapping("/updateTbItem")
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams){
        try {
            return itemService.updateTbItem(tbItem, desc, itemParams);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
