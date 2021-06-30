package com.cl.frontend.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Author l
 * @Date 2021/6/30 11:55
 */
@Component
@FeignClient(value = "cloud-common-content")
public interface CloudCommonContentFeignClient {

    //---------------/service/content
    /**
     * 查询首页大广告位内容
     * @return
     */
    @RequestMapping("/service/content/selectFrontendContentByAD")
    List<Map> selectFrontendContentByAD();


}
