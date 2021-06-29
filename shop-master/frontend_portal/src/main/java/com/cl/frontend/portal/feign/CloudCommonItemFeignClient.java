package com.cl.frontend.portal.feign;

import com.cl.gzshop.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author l
 * @Date 2021/6/28 17:42
 */
@Component
@FeignClient(value = "cloud-common-item")
public interface CloudCommonItemFeignClient {

    //---------------/Service/ItemCategory

    @RequestMapping("/service/itemCategory/selectItemCategoryAll")
    CatResult selectItemCategoryAll();


}
