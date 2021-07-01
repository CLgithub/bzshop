package com.cl.frontend.portal.feign;

import com.cl.gzshop.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author l
 * @Date 2021/7/1 11:47
 */
@Component
@FeignClient("cloud-common-redis")
public interface CloudCommonRedisFeignClient {

    /**
     * 向redis中添加数缓存数据
     * @param catResult
     */
    @RequestMapping("/redis/itemCategory/insertItemCategory")
    void insertItemCategory(@RequestBody CatResult catResult);

    /**
     * 向redis中查询缓存数据
     * @return
     */
    @RequestMapping("/redis/itemCategory/selectItemCategory")
    CatResult selectItemCategory();

}
