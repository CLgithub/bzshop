package com.cl.frontend.portal.service.impl;

import com.cl.frontend.portal.feign.CloudCommonItemFeignClient;
import com.cl.frontend.portal.service.ItemService;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author l
 * @Date 2021/8/7 20:46
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private CloudCommonItemFeignClient cloudCommonItemFeignClient;

    @Override
    public Result selectItemInfo(Long itemId) {
        TbItem tbItem = cloudCommonItemFeignClient.selectItemInfo(itemId);
        if(tbItem!=null){
            return Result.ok(tbItem);
        }
        return Result.error("查询有误");
    }
}
