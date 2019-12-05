package com.xiaoxiao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @project_name:bz_parent
 * @date:2019/9/20:21:55
 * @author:shinelon
 * @Describe: 单点登录服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SSOApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(SSOApp.class, args);
    }
}
