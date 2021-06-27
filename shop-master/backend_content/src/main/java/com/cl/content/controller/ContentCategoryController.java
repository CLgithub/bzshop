package com.cl.content.controller;

import com.cl.content.service.ContentCategoryService;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容分类 tb_Content_Category
 * @Author l
 * @Date 2021/3/16 22:46
 */
@RestController
@RequestMapping("/content")
public class ContentCategoryController {

    @Autowired
    ContentCategoryService contentCategoryService;

    /**
     * 根据当前分类id，查询其子节点
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(@RequestParam(defaultValue = "0") Long id){
        try {
            return contentCategoryService.selectContentCategoryByParentId(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 添加内容分类
     * @param name
     * @param parentId
     * @return
     */
    @RequestMapping("/insertContentCategory")
    public Result insertContentCategory(@RequestParam String name,@RequestParam Long parentId){
        try {
            return contentCategoryService.insertContentCategory(name,parentId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 根据id，删除内容分类
     * @param categoryId
     * @return
     */
    @RequestMapping("/deleteContentCategoryById")
    public Result deleteContentCategoryById(@RequestParam Long categoryId){
        try {
            return contentCategoryService.deleteContentCategoryById(categoryId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 修改内容分类名称
     * @param tbContentCategory
     * @return
     */
    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(TbContentCategory tbContentCategory){
        try {
            return contentCategoryService.updateContentCategory(tbContentCategory);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
