package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.service.RedisArticleService;
import com.xiaoxiao.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.font.TextHitInfo;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
 * @date:2019/12/2:21:12
 * @author:shinelon
 * @Describe:
 */
@Service
public class RedisArticleServiceImpl implements RedisArticleService
{


    /**
     * 缓存的文章个数
     */
    @Value("${ARTICLE_SUM}")
    private String ARTICLE_SUM;


    /**
     * 缓存最新的推荐的文章的数据
     */
    @Value("${ARTICLE_RECOMMEND}")
    private String ARTICLE_RECOMMEND;


    /**
     * 首页博客数据
     */
    @Value("${INDEX_ARTICLE}")
    private String INDEX_ARTICLE;



    @Autowired
    private RedisTemplate<String,Object> redisTemplate;




    /**
     * 插入文章个数到缓存中
     * @param sum
     */
    @Override
    public void insertArticleSumToRedis(Integer sum)
    {
        this.redisTemplate.opsForValue().set(this.ARTICLE_SUM, sum,1,TimeUnit.DAYS);
    }


    /**
     * 获取
     * @return
     */
    @Override
    public Integer getArticleSumToRedis()
    {
        return (Integer) this.redisTemplate.opsForValue().get(this.ARTICLE_SUM);
    }


    /**
     * 删除
     */
    @Override
    public void deleteArticleSumToRedis()
    {
        this.redisTemplate.delete(this.ARTICLE_SUM);
    }


    /**
     * 缓存推荐文章
     * @param result
     */
    @Override
    public void insertArticleNewRecommend(PageResult result)
    {
        this.redisTemplate.opsForValue().set(this.ARTICLE_RECOMMEND, result,1,TimeUnit.DAYS);
    }


    /**
     * 获取缓存中的推荐文章数据
     * @return
     */
    @Override
    public PageResult getArticleNewRecommend()
    {
        return (PageResult) this.redisTemplate.opsForValue().get(this.ARTICLE_RECOMMEND);
    }


    /**
     * 删除缓存中的推荐文章数据
     */
    @Override
    public void deleteArticleNewRecommend()
    {
        this.redisTemplate.delete(this.ARTICLE_RECOMMEND);
    }


    /**
     * 缓存首页的博客数展示列表
     * @param result
     */
    @Override
    public void insertIndexArticle(PageResult result)
    {
        this.redisTemplate.opsForValue().set(this.INDEX_ARTICLE, result, 1, TimeUnit.DAYS);
    }


    /**
     * 获取首页缓存的博客
     * @return
     */
    @Override
    public PageResult getIndexArticle()
    {
        return (PageResult) this.redisTemplate.opsForValue().get(this.INDEX_ARTICLE);
    }


    /**
     * 删除首页缓存博客列表
     */
    @Override
    public void deleteIndexArticle()
    {
        this.redisTemplate.delete(this.INDEX_ARTICLE);
    }


    /**
     * 缓存分类文章
     * @param articleVos
     * @param sortId
     */
    @Override
    public void insertBlogsBySortsToRedis(PageResult articleVos, Long sortId)
    {
        this.redisTemplate.opsForValue().set(String.valueOf(sortId), articleVos,6,TimeUnit.HOURS);
    }


    /**
     *  获取缓存分类文章
     * @param sortId
     * @return
     */
    @Override
    public PageResult getBlogsBySortsToRedis(Long sortId)
    {
        return (PageResult) this.redisTemplate.opsForValue().get(String.valueOf(sortId));
    }


    /**
     * 删除缓存分类文章
     * @param sortId
     */
    @Override
    public void deleteBlogsBySortsToRedis(Long sortId)
    {
        this.redisTemplate.delete(String.valueOf(sortId));
    }


    /**
     *
     * @param articles
     */
    @Override
    public void insertArticleById(XiaoxiaoArticles articles)
    {
        this.redisTemplate.opsForValue().set(String.valueOf(articles.getArticleId()), articles,1,TimeUnit.DAYS);
    }


    /**
     *
     * @param articleId
     * @return
     */
    @Override
    public XiaoxiaoArticles getArticleById(Long articleId)
    {
        return (XiaoxiaoArticles) this.redisTemplate.opsForValue().get(String.valueOf(articleId));
    }
}
