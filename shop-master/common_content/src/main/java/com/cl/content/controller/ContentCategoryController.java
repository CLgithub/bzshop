package com.cl.content.controller;

import com.cl.content.service.ContentCategoryService;
import com.cl.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容分类 tb_Content_Category
 * @Author l
 * @Date 2021/3/16 22:29
 */
@RestController
@RequestMapping("/service/contentCategory")
//@RefreshScope   // 配置中心 动态刷新
public class ContentCategoryController {

    @Resource
    ContentCategoryService contentCategoryService;



    @RequestMapping("/testConfig")
    public long testConfig(){
        return contentCategoryService.long1();
    }

    /**
     * 根据parentid查询子节点
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public List<TbContentCategory> selectContentCategoryByParentId(@RequestParam Long parentId){
        return contentCategoryService.selectContentCategoryByParentId(parentId);
    }

    /**
     * 添加内容分类
     * @param tbContentCategory
     * @return
     */
    @RequestMapping("/insertContentCategory")
    public Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return contentCategoryService.insertContentCategory(tbContentCategory);
    }

    /**
     * 根据id，删除内容分类
     * @param categoryId
     * @return
     */
    @RequestMapping("/deleteContentCategoryById")
    public Integer deleteContentCategoryById(@RequestParam Long categoryId){
        return contentCategoryService.deleteContentCategoryById(categoryId);
    }

    /**
     * 修改内容分类
     * @return
     */
    @RequestMapping("/updateContentCategory")
    public Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return contentCategoryService.updateContentCategory(tbContentCategory);
    }



}
