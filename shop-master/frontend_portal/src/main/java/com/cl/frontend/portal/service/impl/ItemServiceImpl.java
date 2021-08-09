package com.cl.frontend.portal.service.impl;

import com.cl.frontend.portal.feign.CloudCommonItemFeignClient;
import com.cl.frontend.portal.service.ItemService;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItem;
import com.cl.pojo.TbItemDesc;
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

    @Override
    public Result selectItemDescByItemId(Long itemId) {
        TbItemDesc tbItemDesc = cloudCommonItemFeignClient.selectItemDescByItemId(itemId);
        if(tbItemDesc!=null){
            return Result.ok(tbItemDesc);
        }
        return Result.error("查询有误");
    }
}