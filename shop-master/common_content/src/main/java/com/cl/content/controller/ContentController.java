package com.cl.content.controller;

import com.cl.content.service.ContentService;
import com.cl.gzshop.utils.CatResult;
import com.cl.gzshop.utils.PageResult;
import com.cl.pojo.TbContent;
import com.cl.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据分类添加内容
     * @param tbContent
     * @return
     */
    @RequestMapping("/insertTbContent")
    public Integer insertTbContent(@RequestBody TbContent tbContent){
        return contentService.insertTbContent(tbContent);
    }

    /**
     * 根据内容分类内容id，删除内容分类内容
     * @param ids
     * @return
     */
    @RequestMapping("/deleteContentByIds")
    public Integer deleteContentByIds(@RequestParam Long ids){
        return contentService.deleteContentByIds(ids);
    }

    /**
     * 查询首页大广告位内容
     * @return
     */
    @RequestMapping("/selectFrontendContentByAD")
    public List<Map> selectFrontendContentByAD(){
        return contentService.selectFrontendContentByAD();
    }
}
