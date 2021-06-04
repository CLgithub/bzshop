package com.cl.item.service.impl;

import com.cl.item.service.ItemCategoryService;
import com.cl.mapper.TbItemCatMapper;
import com.cl.pojo.TbItemCat;
import com.cl.pojo.TbItemCatExample;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类查询
 * @Author l
 * @Date 2020/9/23 19:40
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{

    @Resource
    private TbItemCatMapper tbItemCatMapper;

    /**
     * 根据分类id查询子节点
     * @param parentId
     * @return
     */
    @Override
    public List<TbItemCat> selectItemCategoryByParentId(Long parentId) {
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);    // 父节点是id的
        criteria.andStatusEqualTo(1);       // status为1
        List<TbItemCat> tbItemCats = this.tbItemCatMapper.selectByExample(example);
        return tbItemCats;
    }
}
