package com.cl.backend.item.service.impl;

import com.cl.backend.item.feign.CloudCommonItemFeignClient;
import com.cl.backend.item.service.ItemService;
import com.cl.gzshop.utils.IDUtils;
import com.cl.gzshop.utils.PageResult;
import com.cl.gzshop.utils.Result;
import com.cl.pojo.TbItem;
import com.cl.pojo.TbItemDesc;
import com.cl.pojo.TbItemParamItem;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 商品管理
 * @Author l
 * @Date 2020/9/24 09:27
 */
@Service
public class ItemServiceImpl implements ItemService{

    @Resource
    private CloudCommonItemFeignClient cloudCommonItemFeignClient;

    @Override
    public Object selectTbItemAllByPage1(Integer page, Integer rows) {
        return cloudCommonItemFeignClient.selectTbItemAllByPage1(page, rows);
    }

    @Override
    public Result selectTbItemAllByPage(Integer page, Integer rows) {
        PageResult pageResult = cloudCommonItemFeignClient.selectTbItemAllByPage(page, rows);
        if(pageResult!=null&&pageResult.getResult()!=null&&pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
        return  Result.error("查无结果");
    }

    @Override
    @LcnTransaction
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams) {
        // 补齐tbItem数据
        long id = IDUtils.genItemId();
//        long id2=Long.parseLong("161526183693706");
        tbItem.setId(id);
        tbItem.setStatus((byte)1);
        tbItem.setUpdated(new Date());
        tbItem.setCreated(new Date());
        Integer integer = cloudCommonItemFeignClient.insertTbItem(tbItem);
        // 补齐商品描述对象
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(new Date());
        Integer integer1 = cloudCommonItemFeignClient.insertItemDesc(tbItemDesc);
        // 补齐商品规格参数
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setCreated(new Date());
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(new Date());
        Integer integer2 = cloudCommonItemFeignClient.insertTbItemParamItem(tbItemParamItem);
        return Result.ok();
    }

    @Override
    @LcnTransaction
    public Result deleteItemById(Long itemId) {
        TbItem tbItem=new TbItem();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)3);
        Integer integer = cloudCommonItemFeignClient.deleteItemById(tbItem);
        if(integer!=null)
            return Result.ok();
        else
            return Result.error("删除失败");
    }

    @Override
    public Result preUpdateItem(Long itemId) {
        Map<String, Object> map = cloudCommonItemFeignClient.preUpdateItem(itemId);
        if(map!=null){
            return Result.ok(map);
        }
        return Result.error("查无结果");
    }

    @Override
    @LcnTransaction
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams) {
        Integer integer = cloudCommonItemFeignClient.updateTbitem(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        Integer integer1 = cloudCommonItemFeignClient.updateItemDesc(tbItemDesc);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(tbItem.getId());
        tbItemParamItem.setParamData(itemParams);
        Integer integer2 = cloudCommonItemFeignClient.updateTbItemParamItem(tbItemParamItem);

        return Result.ok();
    }
}
