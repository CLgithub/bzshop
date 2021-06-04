package com.cl.backend.item.service.impl;

import com.cl.backend.item.feign.CloudCommonItemFeignClient;
import com.cl.backend.item.service.ItemParamService;
import com.cl.gzshop.utils.PageResult;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItemParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author l
 * @Date 2021/2/14 15:02
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Resource
    private CloudCommonItemFeignClient cloudCommonItemFeignClient;

    @Override
    public Result selectItemParamByItemCatId(Long itemCatId) {
        TbItemParam tbItemParam = cloudCommonItemFeignClient.selectItemParamByItemCatId(itemCatId);
        if(tbItemParam!=null){
            return Result.ok(tbItemParam);
        }
        return  Result.error("查无结果");
    }

    @Override
    public Result selectItemParamAll(Integer page, Integer rows) {
        PageResult pageResult = cloudCommonItemFeignClient.selectItemParamAll(page, rows);
        if(pageResult!=null && pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
        return  Result.error("查无结果");
    }

    @Override
    public Result insertItemParam(Long itemCatId, String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(itemCatId);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        Integer integer = cloudCommonItemFeignClient.insertItemParam(tbItemParam);
        if(integer!=null){
            return Result.ok(integer);
        }
        return Result.error("添加失败");
    }

    @Override
    public Result deleteItemParamById(long id) {
        Integer integer = cloudCommonItemFeignClient.deleteItemParamById(id);
        if(integer!=null){
            return Result.ok(integer);
        }
        return Result.error("删除失败");
    }
}
