package com.cl.item.service.impl;

import com.cl.item.service.ItemParamItemService;
import com.cl.mapper.TbItemParamItemMapper;
import com.cl.pojo.TbItemParamExample;
import com.cl.pojo.TbItemParamItem;
import com.cl.pojo.TbItemParamItemExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author l
 * @Date 2021/3/4 22:37
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Resource
    TbItemParamItemMapper itemParamItemMapper;

    @Override
    @LcnTransaction
    public Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem) {
        return itemParamItemMapper.insert(tbItemParamItem);
    }

    @Override
    @LcnTransaction
    public Integer updateTbItemParamItem(TbItemParamItem tbItemParamItem) {
        tbItemParamItem.setUpdated(new Date());
        TbItemParamItemExample tbItemParamItemExample=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(tbItemParamItem.getItemId());
        return this.itemParamItemMapper.updateByExampleSelective(tbItemParamItem,tbItemParamItemExample);
    }
}
