package com.cl.content.service.impl;

import com.cl.content.service.ContentCategoryService;
import com.cl.mapper.TbContentCategoryMapper;
import com.cl.pojo.TbContentCategory;
import com.cl.pojo.TbContentCategoryExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author l
 * @Date 2021/3/16 22:35
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Resource
    TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        return tbContentCategories;
    }

    @Override
    @LcnTransaction
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {
        // 补齐数据
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent(false);
        // 插入当前节点
        int i = tbContentCategoryMapper.insertSelective(tbContentCategory);
        // 修改当前节点的父节点isparent为1
        Long parentId = tbContentCategory.getParentId();
        TbContentCategory parentCategory= tbContentCategoryMapper.selectByPrimaryKey(parentId);
        parentCategory.setIsParent(true);
        parentCategory.setUpdated(new Date());
        int i1 = tbContentCategoryMapper.updateByPrimaryKeySelective(parentCategory);
        return i;
    }

    @Override
    @LcnTransaction
    public Integer deleteContentCategoryById(Long categoryId) {
        // 得到本身
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(categoryId);
        // 删除其子节点，包含子节点的子节点的子节点。。。
        Integer status =this.deleteNode(tbContentCategory);
        // 查询父节点是否有其他子节点，如果有，不处理，如果没有，修改其isP=0
        // 得到父节点
        TbContentCategory parentCategory = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        TbContentCategoryExample e = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = e.createCriteria();
        TbContentCategoryExample.Criteria criteria1 = criteria.andParentIdEqualTo(parentCategory.getId());
        List<TbContentCategory> parentCs= tbContentCategoryMapper.selectByExample(e);
        if(parentCs.size()==0){
            parentCategory.setIsParent(false);
            parentCategory.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKey(parentCategory);
        }
        return status;
    }

    private Integer deleteNode(TbContentCategory tbContentCategory) {
        if(tbContentCategory.getIsParent()){
            TbContentCategoryExample e2=new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria2 = e2.createCriteria();
            criteria2.andParentIdEqualTo(tbContentCategory.getId());
            List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(e2);
            for(TbContentCategory children:tbContentCategories){
                this.deleteNode(children);
                int i = tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
            }
        }else{
            // 删除本身
            int i = tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
        }
        return 200;
    }
}
