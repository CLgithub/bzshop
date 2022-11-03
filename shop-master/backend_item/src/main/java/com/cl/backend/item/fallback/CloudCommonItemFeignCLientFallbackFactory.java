package com.cl.backend.item.fallback;

import com.cl.backend.item.feign.CloudCommonItemFeignClient;
import com.cl.gzshop.utils.PageResult;
import com.cl.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author l
 * @Date 2022/3/8 22:19
 */
@Component
public class CloudCommonItemFeignCLientFallbackFactory implements CloudCommonItemFeignClient {

    Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Integer insertTbItem(TbItem tbItem) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Integer deleteItemById(TbItem tbItem) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Map<String, Object> preUpdateItem(Long itemId) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Integer updateItemById(TbItem tbItem) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public List<TbItemCat> selectItemCategoryByParentId(Long parentId) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public PageResult selectItemParamAll(Integer page, Integer rows) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Integer insertItemParam(TbItemParam tbItemParam) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Integer deleteItemParamById(long id) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Integer insertItemDesc(TbItemDesc tbItemDesc) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Integer updateItemDesc(TbItemDesc tbItemDesc) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }

    @Override
    public Integer updateTbItemParamItem(TbItemParamItem tbItemParamItem) {
        logger.warn(Thread.currentThread().getStackTrace()[1].getMethodName()+" fallback!");
        return null;
    }
}
