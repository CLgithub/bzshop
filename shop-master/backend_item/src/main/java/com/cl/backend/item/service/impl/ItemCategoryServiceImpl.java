package com.cl.backend.item.service.impl;

import com.cl.backend.item.feign.CloudCommonItemFeignClient;
import com.cl.backend.item.service.ItemCategoryService;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItemCat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author l
 * @Date 2020/9/24 09:44
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{

    @Resource
    CloudCommonItemFeignClient cloudCommonItemFeignClient;

    @Override
    public Result selectItemCategoryByParentId(Long id) {
        List<TbItemCat> tbItemCats = cloudCommonItemFeignClient.selectItemCategoryByParentId(id);
        if(tbItemCats!=null&&tbItemCats.size()>0){
            return Result.ok(tbItemCats);
        }
        return  Result.error("查无结果");
    }
}
