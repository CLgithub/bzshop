package com.cl.common.reids.controller;

import com.cl.common.reids.service.ItemCategoryService;
import com.cl.gzshop.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author l
 * @Date 2021/7/1 11:18
 */
@RestController
@RequestMapping("/redis/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 向redis中添加数缓存数据
     * @param catResult
     */
    @RequestMapping("/insertItemCategory")
    public void insertItemCategory(@RequestBody CatResult catResult){
        itemCategoryService.insertItemCategory(catResult);
    }

    /**
     * 向redis中查询缓存数据
     * @return
     */
    @RequestMapping("/selectItemCategory")
    public CatResult selectItemCategory(){
        return itemCategoryService.selectItemCategory();
    }


}
