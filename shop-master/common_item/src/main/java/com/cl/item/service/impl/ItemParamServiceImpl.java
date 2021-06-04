package com.cl.item.service.impl;

import com.cl.gzshop.utils.PageResult;
import com.cl.item.service.ItemParamService;
import com.cl.mapper.TbItemParamMapper;
import com.cl.pojo.TbItem;
import com.cl.pojo.TbItemExample;
import com.cl.pojo.TbItemParam;
import com.cl.pojo.TbItemParamExample;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author l
 * @Date 2021/2/14 14:27
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{

    @Resource
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> tbItemParams = this.tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
        if(tbItemParams!=null&&tbItemParams.size()>0){
            return tbItemParams.get(0);
        }
        return null;
    }

    @Override
    public PageResult selectItemParamAll(Integer page, Integer rows) {
        Page<Object> page1 = PageHelper.startPage(page, rows);

        TbItemParamExample example=new TbItemParamExample();
        List<TbItemParam> tbItemParams = this.tbItemParamMapper.selectByExampleWithBLOBs(example);

        PageInfo<TbItemParam> paramPageInfo=new PageInfo<>();

        PageResult pageResult=new PageResult();
        pageResult.setResult(tbItemParams);
        pageResult.setPageIndex(page);
        pageResult.setTotalPage(paramPageInfo.getTotal());
        return pageResult;
    }

    @Override
    public Integer insertItemParam(TbItemParam tbItemParam) {
        return tbItemParamMapper.insertSelective(tbItemParam);
    }

    @Override
    public Integer deleteItemParamById(long id) {
        return tbItemParamMapper.deleteByPrimaryKey(id);
    }
}
