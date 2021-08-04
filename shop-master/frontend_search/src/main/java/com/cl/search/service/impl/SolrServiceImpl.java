package com.cl.search.service.impl;

import com.cl.gzshop.utils.Result;
import com.cl.gzshop.utils.SolrDocument;
import com.cl.mapper.SolrItemMapper;
import com.cl.pojo.SolrItem;
import com.cl.search.service.SolrService;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

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


    @Override
    public List<SolrDocument> selectByq(String q, Long page, Integer pageSize) {
        //  设置高亮查询条件
        HighlightQuery query=new SimpleHighlightQuery();
        Criteria criteria=new Criteria("item_category_name");   // 放复合域，不然只能插一个字段
        criteria.is(q);
        query.addCriteria(criteria);

        // 设置高亮属性
        HighlightOptions highlightOptions=new HighlightOptions();
        highlightOptions.addField("item_category_name");    // 设置高亮显示的域
        highlightOptions.setSimplePrefix("<em style='color:red'>");  // 设置高亮样式的前缀
        highlightOptions.setSimplePostfix("</em>");     // 设置高亮样式的后缀
        query.setHighlightOptions(highlightOptions);

        //  分页
        query.setOffset((page-1)*pageSize); // 从第几条开始显示
        query.setRows(pageSize);    // 设置每页多少条

        HighlightPage<SolrDocument> highlightPage = solrTemplate.queryForHighlightPage(collection, query, SolrDocument.class);
        List<HighlightEntry<SolrDocument>> highlightEntries = highlightPage.getHighlighted();
        for(HighlightEntry<SolrDocument> highlightEntry : highlightEntries){
            SolrDocument solrDocument = highlightEntry.getEntity();  //  实体对象，没有高亮的原始实体对象
            List<HighlightEntry.Highlight> highlights = highlightEntry.getHighlights();   //拿到高亮部分
            // 如果有高亮，就取高亮
            if(highlights!=null && highlights.size()>0 && highlights.get(0).getSnipplets().size()>0 ){
                solrDocument.setItem_title(highlights.get(0).getSnipplets().get(0));    // 将solrdocument的item_title设置为高亮
//                solrDocument.setItem_category_name(highlights.get(0).getSnipplets().get(0));
            }
        }
        List<SolrDocument> list = highlightPage.getContent();
        return list;
    }


}
