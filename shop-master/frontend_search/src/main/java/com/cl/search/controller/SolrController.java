package com.cl.search.controller;

import com.cl.gzshop.utils.Result;
import com.cl.search.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author l
 * @Date 2021/7/8 23:31
 */
@RestController
@RequestMapping("/search")
public class SolrController {

    @Autowired
    private SolrService solrService;

    /**
     * 向solr索引库导入数据
     * @return
     */
    @RequestMapping("/importAll")
    public Result importAll(){
        return solrService.importAll();
    }
}
