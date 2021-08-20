package com.cl.cart.feign;

import com.cl.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author l
 * @Date 2021/8/20 20:54
 */
@Component
@FeignClient(value = "cloud-common-item")
public interface CloudCommonItemFeignClient {

    @RequestMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId") Long itemId);

}
