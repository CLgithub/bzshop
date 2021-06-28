package com.cl.content.service.impl;

import com.cl.content.service.ContentService;
import com.cl.gzshop.utils.PageResult;
import com.cl.mapper.TbContentMapper;
import com.cl.pojo.TbContent;
import com.cl.pojo.TbContentExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.scene.control.TableColumnBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author l
 * @Date 2021/6/4 16:32
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageHelper.startPage(page,rows);
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(tbContentExample);

        PageInfo<TbContent> pageInfo=new PageInfo<>(tbContents);

        PageResult pageResult=new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setResult(pageInfo.getList());
        pageResult.setTotalPage(pageInfo.getTotal());

        return pageResult;
    }

    @Override
    @LcnTransaction
    public Integer insertTbContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        return tbContentMapper.insert(tbContent);
    }

    @Override
    @LcnTransaction
    public Integer deleteContentByIds(Long ids) {
        return tbContentMapper.deleteByPrimaryKey(ids);
    }

}
