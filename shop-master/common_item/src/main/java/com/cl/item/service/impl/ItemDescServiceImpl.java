package com.cl.item.service.impl;

import com.cl.item.service.ItemDescService;
import com.cl.mapper.TbItemDescMapper;
import com.cl.pojo.TbItemDesc;
import com.cl.pojo.TbItemDescExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author l
 * @Date 2021/3/4 22:24
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Resource
    TbItemDescMapper tbItemDescMapper;

    @Override
    @LcnTransaction
    public Integer insertItemDesc(TbItemDesc tbItemDesc) {
        return tbItemDescMapper.insert(tbItemDesc);
    }

    @Override
    @LcnTransaction
    public Integer updateItemDesc(TbItemDesc tbItemDesc) {
        tbItemDesc.setUpdated(new Date());
        return tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
    }

    @Override
    public TbItemDesc selectItemDescByItemId(Long itemId) {
        TbItemDescExample tbItemDescExample=new TbItemDescExample();
        TbItemDescExample.Criteria criteria=tbItemDescExample.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemDesc> tbItemDescs = tbItemDescMapper.selectByExampleWithBLOBs(tbItemDescExample);
        if(tbItemDescs!=null){
            return tbItemDescs.get(0);
        }
        return null;
    }
}
