package com.cl.item.service;

import com.cl.gzshop.utils.PageResult;
import com.cl.pojo.TbItem;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Author l
 * @Date 2020/9/21 20:34
 */
public interface ItemService {

    /**
     * 查询所有商品并分页
     * @param page
     * @param rows
     * @return
     */
    PageInfo selectTbItemAllByPage1(Integer page, Integer rows);

    /**
     * 查询所有商品并分页
     * @param page
     * @param rows
     * @return
     */
    PageResult selectTbItemAllByPage(Integer page, Integer rows);


    /**
     * 添加TbItem
     * @param tbItem
     * @return
     */
    Integer insertTbItem(TbItem tbItem);

    /**
     * 更新与删除商品
     * 删除是更新status为3 商品状态,1-正常,2-下架,3-删除
     * @param tbItem
     * @return
     */
    Integer updateItemById(TbItem tbItem);

    /**
     * 根据商品id查询，商品、商品描述、商品分类
     * @param itemId
     * @return
     */
    Map<String,Object> preUpdateItem(Long itemId);

    /**
     * 根据商品ID查询商品
     * @param itemId
     * @return
     */
    TbItem selectItemInfo(Long itemId);

}
