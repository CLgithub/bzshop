package com.cl.frontend.portal.service.impl;

import com.cl.frontend.portal.feign.CloudCommonContentFeignClient;
import com.cl.frontend.portal.service.ContentService;
import com.cl.gzshop.utils.CatResult;
import com.cl.gzshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author l
 * @Date 2021/6/30 11:53
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CloudCommonContentFeignClient cloudCommonContentFeignClient;


    @Override
    public Result selectItemCategoryAll() {
        List<Map> listMap= cloudCommonContentFeignClient.selectFrontendContentByAD();
        if(listMap!=null && listMap.size()>0){
            return Result.ok(listMap);
        }
        return Result.error("查询有误");
    }
}
