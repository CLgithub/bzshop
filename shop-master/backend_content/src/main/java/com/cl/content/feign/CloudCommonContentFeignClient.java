package com.cl.content.feign;

import com.cl.gzshop.utils.PageResult;
import com.cl.pojo.TbContent;
import com.cl.pojo.TbContentCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author l
 * @Date 2021/3/16 22:53
 */
@Component
@FeignClient(value = "cloud-common-content")
public interface CloudCommonContentFeignClient {


    // ---------------------/service/contentCategory
    @RequestMapping("/service/contentCategory/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam("parentId") Long parentId);

    @RequestMapping("/service/contentCategory/insertContentCategory")
    Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory);

    @RequestMapping("/service/contentCategory/deleteContentCategoryById")
    Integer deleteContentCategoryById(@RequestParam("categoryId") Long categoryId);

    @RequestMapping("/service/contentCategory/updateContentCategory")
    Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory);

    // ---------------------/service/content
    @RequestMapping("/service/content/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("categoryId") Long categoryId);

    @RequestMapping("/service/content/insertTbContent")
    Integer insertTbContent(@RequestBody TbContent tbContent);

    @RequestMapping("/service/content/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam("ids") Long ids);


}

