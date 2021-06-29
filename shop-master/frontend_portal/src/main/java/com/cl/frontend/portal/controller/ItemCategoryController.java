package com.cl.frontend.portal.controller;

import com.cl.frontend.portal.service.ItemCategoryService;
import com.cl.gzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author l
 * @Date 2021/6/28 17:35
 */
@RestController
@RequestMapping("/frontend/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 查询首页商品分类
     * @return
     */
    @RequestMapping("/selectItemCategoryAll")
    public Result selectItemCategoryAll(){
        try {
            return itemCategoryService.selectItemCategoryAll();
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
