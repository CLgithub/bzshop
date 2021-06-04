package com.cl.backend.item.feign;

import com.cl.gzshop.utils.PageResult;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Author l
 * @Date 2020/9/24 09:30
 */
@Component
@FeignClient(value = "cloud-common-item")
public interface CloudCommonItemFeignClient {

    //----------------/Service/Item
    @RequestMapping("/service/item/selectTbItemAllByPage1")
    Object selectTbItemAllByPage1(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);

    @RequestMapping("/service/item/selectTbItemAllByPage")
    PageResult selectTbItemAllByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);

    @RequestMapping("/service/item/insertTbItem")
    Integer insertTbItem(@RequestBody TbItem tbItem);

    @RequestMapping("/service/item/deleteItemById")
    Integer deleteItemById(@RequestBody TbItem tbItem);

    @RequestMapping("/service/item/preUpdateItem")
    Map<String, Object> preUpdateItem(@RequestParam("itemId") Long itemId);

    @RequestMapping("service/item/updateTbitem")
    Integer updateTbitem(@RequestBody TbItem tbItem);


    //---------------/Service/ItemCategory
    @RequestMapping("/service/itemCategory/selectItemCategoryByParentId")
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam("parentId") Long parentId);

    // --------------/Service/itemParam
    @RequestMapping("service/itemParam/selectItemParamByItemCatId")
    TbItemParam selectItemParamByItemCatId(@RequestParam("itemCatId") Long itemCatId);

    @RequestMapping("service/itemParam/selectItemParamAll")
    PageResult selectItemParamAll(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);

    @RequestMapping("service/itemParam/insertItemParam")
    Integer insertItemParam(@RequestBody TbItemParam tbItemParam);

    @RequestMapping("service/itemParam//deleteItemParamById")
    Integer deleteItemParamById(@RequestParam("id") long id);


    // --------------/Service/ItemDesc
    @RequestMapping("/service/itemDesc/insertItemDesc")
    Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    @RequestMapping("/service/itemDesc/updateItemDesc")
    Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc);


    // --------------/Service/ItemParamItem
    @RequestMapping("/service/itemParamItem/insertTbItemParamItem")
    Integer insertTbItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

    @RequestMapping("/service/itemParamItem/updateTbItemParamItem")
    Integer updateTbItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

}
