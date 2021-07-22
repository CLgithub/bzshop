package com.cl.search.service;

import com.cl.gzshop.utils.Result;

/**
 * @Author l
 * @Date 2021/7/8 23:34
 */
public interface SolrService {

    /**
     * 向solr索引库导入数据
     */
    Result importAll();
}
