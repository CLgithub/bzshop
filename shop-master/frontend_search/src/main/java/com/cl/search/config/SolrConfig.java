package com.cl.search.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @Author l
 * @Date 2021/7/5 17:34
 */
@SpringBootConfiguration
public class SolrConfig {

    @Autowired
    private SolrClient solrClient;

    @Bean
    public SolrTemplate getSolrTemplate(){
        return new SolrTemplate(solrClient);
    }

}
