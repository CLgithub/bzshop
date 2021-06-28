package com.cl.content.service.Impl;

import com.cl.content.feign.CloudCommonContentFeignClient;
import com.cl.content.service.ContentService;
import com.cl.gzshop.utils.PageResult;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbContent;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author l
 * @Date 2021/6/27 18:04
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CloudCommonContentFeignClient cloudCommonContentFeignClient;


    @Override
    public Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageResult pageResult = cloudCommonContentFeignClient.selectTbContentAllByCategoryId(page, rows, categoryId);
        if(pageResult!=null && pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
        return Result.error("查询有误");
    }

    @Override
    @LcnTransaction
    public Result insertTbContent(TbContent tbContent) {
        Integer integer = cloudCommonContentFeignClient.insertTbContent(tbContent);
        if(integer!=null && integer!=0){
            return Result.ok();
        }
        return Result.error("插入失败");
    }

    @Override
    @LcnTransaction
    public Result deleteContentByIds(Long ids) {
        Integer integer = cloudCommonContentFeignClient.deleteContentByIds(ids);
        if(integer!=null && integer!=0){
            return Result.ok();
        }
        return Result.error("删除失败");
    }
}
