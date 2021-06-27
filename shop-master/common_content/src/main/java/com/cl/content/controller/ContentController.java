package com.cl.content.controller;

import com.cl.content.service.ContentService;
import com.cl.gzshop.utils.PageResult;
import com.cl.pojo.TbContent;
import com.cl.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品内容
 * @Author l
 * @Date 2021/6/4 16:29
 */
@RestController
@RequestMapping("/service/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 根据商品内容分类id，查询商品内容
     * @param categoryId
     * @return
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(@RequestParam Integer page, @RequestParam Integer rows, @RequestParam Long categoryId){
        return contentService.selectTbContentAllByCategoryId(page, rows, categoryId);
    }

}
