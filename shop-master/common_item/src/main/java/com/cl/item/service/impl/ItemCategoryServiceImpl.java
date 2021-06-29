package com.cl.item.service.impl;

import com.cl.gzshop.utils.CatNode;
import com.cl.gzshop.utils.CatResult;
import com.cl.item.service.ItemCategoryService;
import com.cl.mapper.TbItemCatMapper;
import com.cl.pojo.TbItemCat;
import com.cl.pojo.TbItemCatExample;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.message.ReusableMessage;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public List<TbItemCat> selectItemCategoryByParentId(Long parentId) {
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);    // 父节点是id的
        criteria.andStatusEqualTo(1);       // status为1
        List<TbItemCat> tbItemCats = this.tbItemCatMapper.selectByExample(example);
        return tbItemCats;
    }

    @Override
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
    private List<CatNode> getCatList(Long parendtId) {
        List<TbItemCat> tbItemCats = this.selectItemCategoryByParentId(parendtId);
        List list=new ArrayList<>();
        int count=0;
        for(TbItemCat tbItemCat:tbItemCats){
            CatNode catNode = new CatNode();
            if(tbItemCat.getIsParent()){ //如果该节点是父节点
                catNode.setName(tbItemCat.getName());
                catNode.setItem(getCatList(tbItemCat.getId()));
                list.add(catNode);
                count++;
                if(count>=18) break;    // 每个节点下，最多只取18个子节点
            }else{
                list.add(tbItemCat.getName());
            }
        }
        return list;
    }

}
