package com.cl.content.service.impl;

import com.cl.content.service.ContentService;
import com.cl.gzshop.utils.CatResult;
import com.cl.gzshop.utils.PageResult;
import com.cl.mapper.TbContentMapper;
import com.cl.pojo.TbContent;
import com.cl.pojo.TbContentExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javafx.scene.control.TableColumnBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author l
 * @Date 2021/6/4 16:32
 */
@Service
@CacheConfig(cacheNames="backend:content")
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Value("${frontend.AD}")
    private Long AD;

    @Override
    @Cacheable(unless="#result == null")
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
    @CacheEvict(allEntries=true)
    public Integer insertTbContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        return tbContentMapper.insert(tbContent);
    }

    @Override
    @LcnTransaction
    @CacheEvict(allEntries=true)
    public Integer deleteContentByIds(Long ids) {
        return tbContentMapper.deleteByPrimaryKey(ids);
    }

    @Override
    @Cacheable(key = "#root.methodName")
    public List<Map> selectFrontendContentByAD() {
        List<Map> listMap=new ArrayList<>();
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(AD);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(tbContentExample);
        for(TbContent tbContent:tbContents){
            Map<String,Object> map=new HashMap<>();
            map.put("heightB",240);
            map.put("src",tbContent.getPic());
            map.put("width",670);
            map.put("alt",tbContent.getSubTitle());
            map.put("srcB",null);
            map.put("widthB",550);
            map.put("href",tbContent.getUrl());
            map.put("height", 240);
            listMap.add(map);
        }
        return listMap;
    }

}
