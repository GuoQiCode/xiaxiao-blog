package com.xiaoxiao.tiny.frontline.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyArticleMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyArticleService;
import com.xiaoxiao.tiny.frontline.utils.PageUtils;
import com.xiaoxiao.utils.PageResult;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForLocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.ResourceHolderSupport;
import org.springframework.util.ResourceUtils;

import java.awt.font.TextHitInfo;
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
 * @date:2019/12/2:20:58
 * @author:shinelon
 * @Describe:
 */
@Service
public class FrontlineTinyArticleServiceImpl implements FrontlineTinyArticleService
{


    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;


    @Value("${MARKED_WORDS_FAULT}")
    private String MARKED_WORDS_FAULT;



    @Autowired
    private FrontlineTinyArticleMapper frontlineTinyArticleMapper;


    @Autowired
    private RedisCacheFeignClient client;


    /**
     * 返回文章个数
     *
     * @return
     */
    @Override
    public Result findArticleSum()
    {

        Integer sum = null;
        try
        {
            sum = this.client.getArticleSumToRedis();
            if (sum != null && sum >= 0)
            {
                return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS, sum);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        sum = this.frontlineTinyArticleMapper.findArticleSum().getSum();

        if (sum != null && sum >= 0)
        {

            try
            {
                this.client.insertArticleSumToRedis(sum);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS, sum);
        }
        return Result.ok(StatusCode.OK, true, this.MARKED_WORDS_SUCCESS, 0);
    }


    /**
     * 查找推荐最新的文章
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findArticleNewRecommend(Integer page, Integer rows)
    {
        try
        {
            PageResult result = this.client.getArticleNewRecommend();

            if (result.getResult() != null && result.getResult().size() > 0)
            {
                return Result.ok(StatusCode.OK, true, this.MARKED_WORDS_SUCCESS, result);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        PageHelper.startPage(page, rows);
        List<XiaoxiaoArticles> articles = this.frontlineTinyArticleMapper.findArticleNewRecommend();
        try
        {
            PageResult result = PageUtils.getResult(new PageInfo<XiaoxiaoArticles>(articles), page);
            this.client.insertArticleNewRecommend(result);
            if(articles != null && articles.size() > 0){
                return Result.ok(StatusCode.OK, true,result);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 获取首页文章列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findIndexArticle(Integer page, Integer rows)
    {
        PageResult result = null;
        try
        {
             result = this.client.getIndexArticle();
             if(result.getResult() != null && result.getResult().size() > 0)
             {
                 return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS,result);
             }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        List<XiaoxiaoArticleVo> articles = this.frontlineTinyArticleMapper.findIndexArticle();
        PageResult result1 = PageUtils.getResult(new PageInfo<XiaoxiaoArticleVo>(articles), page);
        if(articles != null && articles.size() > 0)
        {
            try
            {
                this.client.insertIndexArticle(result1);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS,result1);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_SUCCESS);
    }


    /**
     * 获取分类文章
     * @param page
     * @param rows
     * @param sortId
     * @return
     */
    @Override
    public Result findBlogsBySorts(Integer page, Integer rows, Long sortId)
    {
        try
        {
            List<XiaoxiaoArticleVo> blogsBySortsToRedis = this.client.getBlogsBySortsToRedis(sortId);
            if(blogsBySortsToRedis != null && blogsBySortsToRedis.size() > 0){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,blogsBySortsToRedis);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        List<XiaoxiaoArticleVo> blogsBySorts = this.frontlineTinyArticleMapper.findBlogsBySorts(sortId);
        if(blogsBySorts != null && blogsBySorts.size() > 0){
            try
            {
                this.client.insertBlogsBySortsToRedis(blogsBySorts, sortId);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true,this
            .MARKED_WORDS_SUCCESS,PageUtils.getResult(new PageInfo<XiaoxiaoArticleVo>(blogsBySorts), page));
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }
}
