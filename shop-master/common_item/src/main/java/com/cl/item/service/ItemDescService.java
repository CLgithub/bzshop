package com.cl.item.service;

import com.cl.pojo.TbItemDesc;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 商品描述
 * @Author l
 * @Date 2021/3/4 22:22
 */
public interface ItemDescService {

    /**
     * 添加商品描述
     * @param tbItemDesc
     * @return
     */
    Integer insertItemDesc(TbItemDesc tbItemDesc);

    /**
     * 更新商品描述
     * @param tbItemDesc
     * @return
     */
    Integer updateItemDesc(TbItemDesc tbItemDesc);
}
