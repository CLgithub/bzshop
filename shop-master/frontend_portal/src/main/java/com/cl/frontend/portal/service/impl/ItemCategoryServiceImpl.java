package com.cl.frontend.portal.service.impl;

import com.cl.frontend.portal.feign.CloudCommonItemFeignClient;
import com.cl.frontend.portal.service.ItemCategoryService;
import com.cl.gzshop.utils.CatResult;
import com.cl.gzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author l
 * @Date 2021/6/28 17:41
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Resource
    CloudCommonItemFeignClient cloudCommonItemFeignClient;

    @Override
    public Result selectItemCategoryAll() {
        CatResult catResult = cloudCommonItemFeignClient.selectItemCategoryAll();
        if(catResult!=null){
            return Result.ok(catResult);
        }
        return Result.error("查询有误");
    }

}
