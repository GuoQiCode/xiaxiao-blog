package com.xiaoxiao.search.config;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
/**
 * @project_name:bz_parent
 * @date:2019/9/19:14:01
 * @author:shinelon
 * @Describe: 配置Solr的相关配置
 */
@Configuration
public class SolrConfig
{
    @Autowired
    private SolrClient solrClient;


    /**
     * 将SpringBoot创建好的SolrClient对象注入给SolrTemplate使用SolrTemplate操作Solr
     * @return
     */
    @Bean
    public SolrTemplate solrTemplate(){
        return new SolrTemplate(solrClient);
    }

}
