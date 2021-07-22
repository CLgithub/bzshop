package com.cl.search.service.impl;

import com.cl.gzshop.utils.Result;
import com.cl.mapper.SolrItemMapper;
import com.cl.pojo.SolrItem;
import com.cl.search.service.SolrService;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;
import sun.misc.Cache;

import java.util.List;

/**
 * @Author l
 * @Date 2021/7/8 23:35
 */
@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    private SolrItemMapper solrItemMapper;
    @Autowired
    private SolrTemplate solrTemplate;

    @Value("${spring.data.solr.core}")
    private String collection;

    @Override
    public Result importAll() {
        try {
            // 查询数据
            List<SolrItem> itemList = solrItemMapper.getItemList();
            // 将数据添加到索引库
            for(SolrItem solrItem:itemList){
                // 创建solrInputDocument对象
                SolrInputDocument solrInputDocument=new SolrInputDocument();
                solrInputDocument.setField("id",solrItem.getId());
                solrInputDocument.setField("item_title",solrItem.getTitle());
                solrInputDocument.setField("item_sell_point1",solrItem.getSell_point());
                solrInputDocument.setField("item_price",solrItem.getPrice());
                solrInputDocument.setField("item_image",solrItem.getImage());
                solrInputDocument.setField("item_category_name",solrItem.getName());
                solrInputDocument.setField("item_desc",solrItem.getItem_desc());
                solrTemplate.saveDocument(collection, solrInputDocument);
            }
            solrTemplate.commit(collection);
            return Result.ok();
        } catch(Exception e){
            e.printStackTrace();
        }
        return Result.error("导入失败");
    }
}
