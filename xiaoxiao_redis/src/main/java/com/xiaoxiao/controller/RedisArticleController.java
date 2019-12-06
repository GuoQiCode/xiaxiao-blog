package com.xiaoxiao.controller;

import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.service.RedisArticleService;
import com.xiaoxiao.utils.PageResult;
import com.xiaoxiao.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.objects.annotations.Optimistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
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
 * @date:2019/12/2:21:09
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/redis_article_service")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "缓存文章")
public class RedisArticleController
{

    @Autowired
    private RedisArticleService redisArticleService;


    @ApiOperation(value = "插入文章个数到缓存中", notes = "插入文章个数到缓存中")
    @PostMapping(value = "/insert_article_sum_to_redis")
    public void insertArticleSumToRedis(@RequestParam("sum") Integer sum)
    {
        this.redisArticleService.insertArticleSumToRedis(sum);
    }


    @ApiOperation(value = "获取文章个数", response = Integer.class, notes = "获取文章个数")
    @PostMapping(value = "/get_article_sum_to_redis")
    public Integer getArticleSumToRedis()
    {
        return this.redisArticleService.getArticleSumToRedis();
    }


    @ApiOperation(value = "删除缓存个数数据", notes = "删除缓存个数数据")
    @PostMapping(value = "/delete_article_sum_to_redis")
    public void deleteArticleSumToRedis()
    {
        this.redisArticleService.deleteArticleSumToRedis();
    }


    @ApiOperation(value = "缓存最新推荐的文章", notes = "缓存最新推荐的文章")
    @PostMapping(value = "/insert_article_new_recommend")
    public void insertArticleNewRecommend(@RequestBody PageResult result)
    {
        this.redisArticleService.insertArticleNewRecommend(result);
    }


    @ApiOperation(value = "获取缓存中存储的推荐文章信息", response = Result.class, notes = "获取缓存中存储的推荐文章信息")
    @PostMapping(value = "/get_article_new_recommend")
    public PageResult getArticleNewRecommend()
    {
        return this.redisArticleService.getArticleNewRecommend();
    }


    @ApiOperation(value = "删除缓存的推荐文章中数据", notes = "删除缓存的推荐文章中数据")
    @PostMapping(value = "/delete_article_new_recommend")
    public void deleteArticleNewRecommend()
    {
        this.redisArticleService.deleteArticleNewRecommend();
    }


    @ApiOperation(value = "缓存首页博客列表数据", notes = "缓存首页博客列表数据")
    @PostMapping(value = "/insert_index_article")
    public void insertIndexArticle(@RequestBody PageResult result)
    {
        this.redisArticleService.insertIndexArticle(result);
    }


    @ApiOperation(value = "获取首页缓存中的数据", response = PageResult.class, notes = "获取首页缓存中的数据")
    @PostMapping(value = "/get_index_article")
    public PageResult getIndexArticle()
    {
        return this.redisArticleService.getIndexArticle();
    }


    @ApiOperation(value = "删除首页缓存信息", notes = "删除首页缓存信息")
    @PostMapping(value = "/delete_index_article")
    public void deleteIndexArticle()
    {
        this.redisArticleService.deleteIndexArticle();
    }


    @ApiOperation(value = "插入分类文章缓存数据", notes = "插入分类文章缓存数据")
    @PostMapping(value = "/insert_blogs_by_sorts_to_redis")
    public void insertBlogsBySortsToRedis(@RequestBody PageResult result
            , @RequestParam(name = "sortId") Long sortId)
    {
        this.redisArticleService.insertBlogsBySortsToRedis(result, sortId);
    }


    @ApiOperation(value = "获取分类文章缓存数据", notes = "获取分类文章缓存数据")
    @PostMapping(value = "/get_blogs_by_sorts_to_redis")
    public PageResult getBlogsBySortsToRedis(@RequestParam(name = "sortId") Long sortId)
    {
        return this.redisArticleService.getBlogsBySortsToRedis(sortId);
    }


    @ApiOperation(value = "删除分类文章缓存",notes = "删除分类文章缓存")
    @PostMapping(value = "/delete_blogs_by_sorts_to_redis")
    public void deleteBlogsBySortsToRedis(@RequestParam(name = "sortId") Long sortId)
    {
        this.redisArticleService.deleteBlogsBySortsToRedis(sortId);
    }

}
