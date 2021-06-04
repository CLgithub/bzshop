package com.cl.backend.item.controller;

import com.cl.backend.item.service.ItemCategoryService;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类目controller
 * @Author l
 * @Date 2020/9/24 09:41
 */
@RestController
@RequestMapping(("/backend/itemCategory"))
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 根据商品类目，返回子节点
     * @param id
     * @return
     */
    @RequestMapping("/selectItemCategoryByParentId")
    public Result selectItemCategoryByParentId(@RequestParam(value = "id", defaultValue = "0") Long id){
        try {
            return itemCategoryService.selectItemCategoryByParentId(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
