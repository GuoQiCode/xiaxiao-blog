package com.xiaoxiao.tiny.frontline.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiaoxiao.pojo.XiaoxiaoAdvertising;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyAdvertisingMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyAdvertisingService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import io.swagger.annotations.SwaggerDefinition;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**

 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/16:18:00
 * @author:shinelon
 * @Describe:
 */
@Service
@SuppressAjWarnings("all")
public class FrontlineTinyAdvertisingServiceImpl implements FrontlineTinyAdvertisingService {

    @Autowired
    private FrontlineTinyAdvertisingMapper frontlineTinyAdvertisingMapper;

    @Autowired
    private RedisCacheFeignClient client;

    @Override
    public Result findAdvertisingAll(Integer page, Integer rows) {
        try {
            List<XiaoxiaoAdvertising> advertisingToRedis = this.client.getAdvertisingToRedis();
            if (advertisingToRedis.size() > 0 && advertisingToRedis != null) {
                return Result.ok(StatusCode.OK, true, Result.MARKED_WORDS_SUCCESS, advertisingToRedis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        List<XiaoxiaoAdvertising> list = this.frontlineTinyAdvertisingMapper.findAdvertisingAll();
        if (list != null && list.size() > 0) {
            try {
                this.client.insertAdvertisingToRedis(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true, Result.MARKED_WORDS_SUCCESS, list);
        }
        return Result.error(StatusCode.ERROR, Result.MARKED_WORDS_FAULT);
    }
}
