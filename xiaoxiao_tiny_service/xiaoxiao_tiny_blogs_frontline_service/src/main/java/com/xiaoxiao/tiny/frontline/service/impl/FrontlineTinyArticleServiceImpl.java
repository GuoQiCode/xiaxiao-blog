package com.xiaoxiao.tiny.frontline.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.LADD;
import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoLabelVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.tiny.frontline.feign.RedisCacheFeignClient;
import com.xiaoxiao.tiny.frontline.mapper.FrontlineTinyArticleMapper;
import com.xiaoxiao.tiny.frontline.service.FrontlineTinyArticleService;
import com.xiaoxiao.tiny.frontline.utils.PageUtils;
import com.xiaoxiao.utils.MarkdownUtils;
import com.xiaoxiao.utils.PageResult;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForLocalTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.AATextRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
             result = this.client.getIndexArticle(page);
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
                this.client.insertIndexArticle(result1,page);
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
            PageResult blogsBySortsToRedis = this.client.getBlogsBySortsToRedis(sortId);
            if(blogsBySortsToRedis != null && blogsBySortsToRedis.getResult().size() > 0){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,blogsBySortsToRedis);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        List<XiaoxiaoArticleVo> blogsBySorts = this.frontlineTinyArticleMapper.findBlogsBySorts(sortId);
        PageResult result = PageUtils.getResult(new PageInfo<XiaoxiaoArticleVo>(blogsBySorts), page);
        if(blogsBySorts != null && blogsBySorts.size() > 0){
            try
            {
                this.client.insertBlogsBySortsToRedis(result, sortId);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true,this
            .MARKED_WORDS_SUCCESS,result);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }



    @Override
    public Result findBlogById(Long articleId)
    {
        try
        {
            /**
             * 插入浏览量
             */
            Integer articleView = this.client.getArticleView(articleId);
            if(articleView != null && articleView > 0){
                this.client.insertArticleView(++articleView,articleId);
            }else {
                this.client.insertArticleView(1, articleId);
            }



            /**
             * 获取缓存的数据
             */
            XiaoxiaoArticleVo articleById = this.client.getArticleById(articleId);
            if(articleById != null){
                return Result.ok(StatusCode.OK,true,this.MARKED_WORDS_SUCCESS,articleById );
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        XiaoxiaoArticleVo article = this.frontlineTinyArticleMapper.findBlogById(articleId);
        /**
         * 转换内容为HTML文档
         */
        String s = MarkdownUtils.markdownToHtmlExtensions(article.getArticleContent());
        article.setArticleContent(s);
        if(article != null)
        {
            try
            {
                this.client.insertArticleById(article);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,article);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 获取按照年份分类的文章
     * @return
     */
    @Override
    public Result findArticleOfYear()
    {
        try
        {
            Map<String, List<XiaoxiaoArticleVo>> articleArchive = this.client.getArticleArchive();
            if(articleArchive != null && articleArchive.size() > 0){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,articleArchive);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        List<String> years = frontlineTinyArticleMapper.findArticleYear();
        Map<String,List<XiaoxiaoArticleVo>> map = new HashMap<>();
        /**
         * 根据年份循环获取文章
         */
        for (String y: years
             )
        {
            map.put(y,this.frontlineTinyArticleMapper.findArticleOfYear(y));
        }
        if(map != null && map.size() > 0){
            try
            {
                this.client.insertArticleArchive(map);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK,true,this.MARKED_WORDS_SUCCESS,map);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     *根据标签查询文章
     * @param labelId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findArticleByLabelId(Long labelId, Integer page, Integer rows)
    {
        try
        {
            PageResult result = this.client.getArticleByLabelId(labelId);
            if(result != null && result.getResult().size() > 0){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,result);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        List<XiaoxiaoArticleVo> articleByLabelId = this.frontlineTinyArticleMapper.findArticleByLabelId(labelId);
        PageResult result = PageUtils.getResult(new PageInfo<XiaoxiaoArticleVo>(articleByLabelId), page);
        if(result != null && result.getResult().size() > 0){
            try
            {
                this.client.insertArticleByLabelId(result, labelId);
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,result);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }





    @Override
    public Result findArticleBySortSum(Long sortId)
    {
        try
        {
            XiaoxiaoSortsVo articleSortSum = this.client.getArticleSortSum(sortId);
            if(articleSortSum != null){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,articleSortSum);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        XiaoxiaoSortsVo articleBySortSum = this.frontlineTinyArticleMapper.findArticleBySortSum(sortId);
        if(articleBySortSum != null){
            try
            {
                this.client.insertArticleSortSum(sortId, articleBySortSum);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK,true,this.MARKED_WORDS_SUCCESS,articleBySortSum);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }







    @Override
    public Result findArticleLabelSum(Long labelId)
    {
        try
        {
            XiaoxiaoLabelVo articleLabelSum1 = this.client.getArticleLabelSum(labelId);
            if(articleLabelSum1 != null){
                return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,articleLabelSum1);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        XiaoxiaoLabelVo articleLabelSum = this.frontlineTinyArticleMapper.findArticleLabelSum(labelId);
        if(articleLabelSum != null ){
            try
            {
                this.client.insertArticleLabelSum(labelId, articleLabelSum);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, true,this.MARKED_WORDS_SUCCESS,articleLabelSum);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }
}
