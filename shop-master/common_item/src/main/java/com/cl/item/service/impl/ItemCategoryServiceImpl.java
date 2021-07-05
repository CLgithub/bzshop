package com.cl.item.service.impl;

import com.cl.gzshop.utils.CatResult;
import com.cl.item.service.ItemCategoryService;
import com.cl.mapper.TbItemCatMapper;
import com.cl.pojo.TbItemCat;
import com.cl.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品分类查询
 * @Author l
 * @Date 2020/9/23 19:40
 */
@Service
@CacheConfig(cacheNames="itemCategory")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ItemCategoryServiceImpl implements ItemCategoryService{

//    private ItemCategoryService _this;
//
//    @Autowired
//    public ItemCategoryServiceImpl(ItemCategoryService itemCategoryService){
//        _this=itemCategoryService;
//    }

    @Resource
    private TbItemCatMapper tbItemCatMapper;
    @Resource
    private ItemCategoryService itemCategoryService;

    @Override
    @Cacheable(unless="#result == null")
    public List<TbItemCat> selectItemCategoryByParentId(Long parentId) {
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);    // 父节点是id的
        criteria.andStatusEqualTo(1);       // status为1
        List<TbItemCat> tbItemCats = this.tbItemCatMapper.selectByExample(example);
        return tbItemCats;
    }

    @Override
    @Cacheable(key = "#root.methodName")
    public CatResult selectItemCategoryAll() {
        CatResult catResult=new CatResult();
        // 查询商品分类
        catResult.setData(getCatList(0L));
        return catResult;
    }

    /**
     * 查询该节点下的子节点商品分类
     * @param parendtId
     * @return
     */
    private List<Map<String,Object>> getCatList(Long parendtId) {
        List<TbItemCat> tbItemCats = itemCategoryService.selectItemCategoryByParentId(parendtId);
        List list=new ArrayList<>();
        int count=0;
        for(TbItemCat tbItemCat:tbItemCats){
            HashMap<String, Object> map = new HashMap<>();
            if(tbItemCat.getIsParent()){ //如果该节点是父节点
                map.put("n",tbItemCat.getName());
                map.put("i",getCatList(tbItemCat.getId()));
                list.add(map);
                count++;
                if(count>=18) break;    // 每个节点下，最多只取18个子节点
            }else{
                list.add(tbItemCat.getName());
            }
        }
        return list;
    }

}
