package com.cl.frontend.portal.service.impl;

import com.cl.frontend.portal.feign.CloudCommonItemFeignClient;
import com.cl.frontend.portal.service.ItemCategoryService;
import com.cl.gzshop.utils.CatResult;
import com.cl.gzshop.utils.Result;
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

//    @Resource
//    CloudCommonRedisFeignClient cloudCommonRedisFeignClient;

    @Override
    public Result selectItemCategoryAll() {
        // 硬编码实现缓存，其实可以使用注解方式实现缓存，且在底层服务中去实现，可复用缓存
//        try{
//            // 查询缓存
//            CatResult catResult = cloudCommonRedisFeignClient.selectItemCategory();
//            if(catResult!=null&&catResult.getData()!=null&&catResult.getData().size()>0){
//                return Result.ok(catResult);
//            }else{
//                // 查询数据库
//                catResult = cloudCommonItemFeignClient.selectItemCategoryAll();
//                if(catResult!=null&&catResult.getData()!=null&&catResult.getData().size()>0){
//                    cloudCommonRedisFeignClient.insertItemCategory(catResult);
//                    return Result.ok(catResult);
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        // 查询数据库
        CatResult catResult = cloudCommonItemFeignClient.selectItemCategoryAll();
        if(catResult!=null&&catResult.getData()!=null&&catResult.getData().size()>0){
            return Result.ok(catResult);
        }
        return Result.error("查询有误");
    }

}
