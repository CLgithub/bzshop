package com.cl.search.service;

import com.cl.gzshop.utils.Result;
import com.cl.gzshop.utils.SolrDocument;

import java.util.List;

/**
 * @Author l
 * @Date 2021/7/8 23:34
 */
public interface SolrService {

    /**
     * 向solr索引库导入数据
     */
    Result importAll();

    /**
     * 搜索数据
     * @param q
     * @param page
     * @param pageSize
     * @return
     */
    List<SolrDocument> selectByq(String q, Long page, Integer pageSize);
}
