package com.cl.item.controller;

import com.cl.gzshop.utils.CatResult;
import com.cl.item.service.ItemCategoryService;
import com.cl.pojo.TbItemCat;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类目service
 * @Author l
 * @Date 2020/9/23 19:35
 */
@RestController
@RequestMapping("/service/itemCategory")
public class ItemCategoryController {

    @Resource
    private ItemCategoryService itemCategoryService;

    /**
     * 通过商品类目id，查询商品类目
     * @param parentId
     * @return
     */
    @RequestMapping("/selectItemCategoryByParentId")
    public List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long parentId){
        return itemCategoryService.selectItemCategoryByParentId(parentId);
    }

    /**
     * 查询首页商品分类
     * @return
     */
    @RequestMapping("/selectItemCategoryAll")
    public CatResult selectItemCategoryAll(){
        return itemCategoryService.selectItemCategoryAll();
    }

}
