package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoLabels;
import com.xiaoxiao.service.RedisLabelService;
import com.xiaoxiao.utils.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\  =  /O
 * ____/`---'\____
 * .'  \\|     |//  `.
 * /  \\|||  :  |||//  \
 * /  _||||| -:- |||||-  \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |   |
 * \  .-\__  `-`  ___/-. /
 * ___`. .'  /--.--\  `. . __
 * ."" '<  `.___\_<|>_/___.'  >'"".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/1:22:29
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_label_service")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "标签的缓存")
public class RedisLabelController
{


    @Autowired
    private RedisLabelService redisLabelService;


    @ApiOperation(value = "插入缓存", notes = "插入缓存")
    @PostMapping(value = "/insert_label_to_redis")
    public void insertLabelToRedis(@RequestBody List<XiaoxiaoLabels> labels)
    {
        this.redisLabelService.insertLabelToRedis(labels);
    }


    @ApiOperation(value = "获取缓存中的数据", response = XiaoxiaoLabels.class, notes = "获取缓存中的数据")
    @PostMapping(value = "/get_label_to_redis")
    public List<XiaoxiaoLabels> getLabelToRedis()
    {
        return this.redisLabelService.getLabelToRedis();
    }


    @ApiOperation(value = "缓存首页标签文章数据", notes = "缓存首页标签文章数据")
    @PostMapping(value = "/insert_index_article_label")
    public void insertIndexArticleLabel(@RequestBody PageResult result)
    {
        this.redisLabelService.insertIndexArticleLabel(result);
    }


    @ApiOperation(value = "获取缓存中的首页标签文章数据", response = PageResult.class, notes = "获取缓存中的首页标签文章数据")
    @PostMapping(value = "/get_index_article_label")
    public PageResult getIndexArticleLabel()
    {
        return this.redisLabelService.getIndexArticleLabel();
    }


    @ApiOperation(value = "删除缓存中的首页标签文章数据", notes = "删除缓存中的首页标签文章数据")
    @PostMapping(value = "/delete_index_article_label")
    public void deleteIndexArticleLabel()
    {
        this.redisLabelService.deleteIndexArticleLabel();
    }


}
