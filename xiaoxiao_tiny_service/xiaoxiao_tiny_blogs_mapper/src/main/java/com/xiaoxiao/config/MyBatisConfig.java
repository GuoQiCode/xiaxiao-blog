package com.xiaoxiao.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @project_name:bz_parent
 * @date:2019/9/17:15:04
 * @author:shinelon
 * @Describe:
 */
@Configuration
public class MyBatisConfig
{

    /**
     * 配置分页
     * @return
     */
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //配置分页插件的属性
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
