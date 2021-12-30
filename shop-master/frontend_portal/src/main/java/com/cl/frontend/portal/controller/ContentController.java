package com.cl.frontend.portal.controller;

import com.cl.frontend.portal.service.ContentService;
import com.cl.gzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 首页内容管理
 * @Author l
 * @Date 2021/6/30 11:44
 */
@RestController
@RequestMapping("/frontend/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 查询首页大广告位内容
     * @return
     */
    @RequestMapping("/selectFrontendContentByAD")
    public Result selectFrontendContentByAD(){
        try {
//            Thread.sleep(2000);
            return contentService.selectItemCategoryAll();
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
