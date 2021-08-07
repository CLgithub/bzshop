package com.cl.item.service.impl;

import com.cl.gzshop.utils.PageResult;
import com.cl.item.service.ItemService;
import com.cl.mapper.TbItemCatMapper;
import com.cl.mapper.TbItemDescMapper;
import com.cl.mapper.TbItemMapper;
import com.cl.mapper.TbItemParamItemMapper;
import com.cl.pojo.*;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author l
 * @Date 2020/9/21 20:35
 */
@Service
public class ItemServiceImpl implements ItemService{

    @Resource
    private TbItemMapper tbItemMapper;
    @Resource
    private TbItemDescMapper tbItemDescMapper;
    @Resource
    private TbItemParamItemMapper  tbItemParamItemMapper;
    @Resource
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public PageInfo selectTbItemAllByPage1(Integer page, Integer rows) {
        Page<Object> page1= PageHelper.startPage(page, rows);

        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo((byte) 1);    // 添加条件status为1的
        List<TbItem> list=tbItemMapper.selectByExample(example);


        PageInfo<TbItem> pageInfo=new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        Page<Object> page1 = PageHelper.startPage(page, rows);

        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andStatusEqualTo((byte) 1);  // 添加条件status为1的
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);

        PageInfo<TbItem> pageInfo=new PageInfo<>(tbItems);

        PageResult pageResult=new PageResult();
        pageResult.setResult(tbItems);
        pageResult.setPageIndex(page);
        pageResult.setTotalPage(pageInfo.getTotal());
        return pageResult;
    }

    @Override
    @LcnTransaction
    public Integer insertTbItem(TbItem tbItem) {
        int insert = this.tbItemMapper.insert(tbItem);
        return insert;
    }

    @Override
    @LcnTransaction
    public Integer updateItemById(TbItem tbItem) {
        tbItem.setUpdated(new Date());
        int i = tbItemMapper.updateByPrimaryKeySelective(tbItem); // 有哪个字段，更新哪个字段，没有不更新
        return i;
    }

    @Override
    public Map<String, Object> preUpdateItem(Long itemId) {
        Map<String, Object> map=new HashMap<>();
        // 根据商品id查询商品
        TbItem tbItem = this.tbItemMapper.selectByPrimaryKey(itemId);
        map.put("item",tbItem);
        // 根据商品id查询商品描述
        TbItemDesc tbItemDesc = this.tbItemDescMapper.selectByPrimaryKey(itemId);
        map.put("itemDesc", tbItemDesc.getItemDesc());
        // 根据商品id查询商品规格参数
        TbItemParamItemExample tbItemParamItemExample=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItems = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);   // 因为有可能是长文本，所以使用selectByExampleWithBLOBs
        if(tbItemParamItems!=null && tbItemParamItems.size()>0){
            map.put("itemParamItem", tbItemParamItems.get(0));
        }
        // 根据商品id查询商品分类
        TbItemCat tbItemCat = this.tbItemCatMapper.selectByPrimaryKey(tbItem.getCid());
        map.put("itemCat", tbItemCat.getName());
        return map;
    }

    @Override
    public TbItem selectItemInfo(Long itemId) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        return tbItem;
    }
}
