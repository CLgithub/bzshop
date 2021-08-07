package com.cl.frontend.portal.feign;

import com.cl.gzshop.utils.CatResult;
import com.cl.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author l
 * @Date 2021/6/28 17:42
 */
@Component
@FeignClient(value = "cloud-common-item")
public interface CloudCommonItemFeignClient {

    //---------------/service/itemCategory

    @RequestMapping("/service/itemCategory/selectItemCategoryAll")
    CatResult selectItemCategoryAll();

    /**
     * 根据商品ID查询商品
     * @param itemId
     * @return
     */
    @RequestMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId") Long itemId);
}
