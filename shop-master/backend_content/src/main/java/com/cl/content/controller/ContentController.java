package com.cl.content.controller;

import com.cl.content.service.ContentService;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author l
 * @Date 2021/6/27 17:59
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 根据商品内容分类id，查询商品内容
     * @param categoryId
     * @return
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows,@RequestParam Long categoryId){
        try {
            return contentService.selectTbContentAllByCategoryId(page,rows,categoryId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 根据分类添加内容
     * @param tbContent
     * @return
     */
    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent){
        try {
            return contentService.insertTbContent(tbContent);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 根据id，删除内容
     * @param ids
     * @return
     */
    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(@RequestParam Long ids){
        try {
            return contentService.deleteContentByIds(ids);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
