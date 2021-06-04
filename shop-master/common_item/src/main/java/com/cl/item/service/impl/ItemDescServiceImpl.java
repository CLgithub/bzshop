package com.cl.item.service.impl;

import com.cl.item.service.ItemDescService;
import com.cl.mapper.TbItemDescMapper;
import com.cl.pojo.TbItemDesc;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.ServiceMode;
import java.util.Date;

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
}
