package com.cl.content.service.Impl;

import com.cl.content.feign.CloudCommonContentFeignClient;
import com.cl.content.service.ContentCategoryService;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbContentCategory;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author l
 * @Date 2021/3/16 22:48
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private CloudCommonContentFeignClient cloudCommonContentFeignClient;


    @Override
    public Result selectContentCategoryByParentId(Long id) {
        List<TbContentCategory> tbContentCategories = cloudCommonContentFeignClient.selectContentCategoryByParentId(id);
        if(tbContentCategories!=null && tbContentCategories.size()>0){
            return Result.ok(tbContentCategories);
        }
        return Result.error("查无结果");
    }

    @Override
    @LcnTransaction
    public Result insertContentCategory(String name, Long parentId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        Integer integer = cloudCommonContentFeignClient.insertContentCategory(tbContentCategory);
        if(integer!=null && integer!=0){
            return Result.ok(integer);
        }
        return Result.error("插入失败");
    }

    @Override
    @LcnTransaction
    public Result deleteContentCategoryById(Long categoryId) {
        Integer integer = cloudCommonContentFeignClient.deleteContentCategoryById(categoryId);
        if(integer==200){
            return Result.ok(integer);
        }
        return Result.error("删除失败");
    }

    @Override
    @LcnTransaction
    public Result updateContentCategory(TbContentCategory tbContentCategory) {
        Integer integer = cloudCommonContentFeignClient.updateContentCategory(tbContentCategory);
        if(integer!=null && integer!=0){
            return Result.ok(integer);
        }
        return Result.error("修改失败");
    }
}
