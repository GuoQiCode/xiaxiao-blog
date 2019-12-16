package com.xiaoxiao.service.backend.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.feign.RedisCacheFeignClient;
import com.xiaoxiao.feign.SearchFeignClient;
import com.xiaoxiao.mapper.ArtcleMapper;
import com.xiaoxiao.mapper.SetArticleLabelMapper;
import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.XiaoxiaoSetArtitleLabel;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.service.backend.TinyArticleService;
import com.xiaoxiao.utils.IDUtils;
import com.xiaoxiao.utils.PageUtils;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
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
 * @date:2019/11/29:16:46
 * @author:shinelon
 * @Describe:
 */
@Service
public class TinyArticleServiceImpl implements TinyArticleService
{

    @Autowired
    private ArtcleMapper artcleMapper;

    @Autowired
    private SetArticleLabelMapper setArticleLabelMapper;

    @Autowired
    private RedisCacheFeignClient client;

    @Autowired
    private SearchFeignClient searchFeignClient;

    @Value("${MARKED_WORDS_SUCCESS}")
    private String MARKED_WORDS_SUCCESS;

    @Value("${MARKED_WORDS_FAULT}}")
    private String MARKED_WORDS_FAULT;


    /**
     * 查询全部的数据
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findAllArticle(Integer page, Integer rows)
    {
        PageHelper.startPage(page, rows);
        List<XiaoxiaoArticleVo> allArticle = this.artcleMapper.findAllArticle();
        if (allArticle != null && allArticle.size() > 0)
        {
            return Result.ok(StatusCode.OK, true, this.MARKED_WORDS_SUCCESS, PageUtils.getResult(new PageInfo<XiaoxiaoArticleVo>(allArticle), page));
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 删除
     *
     * @param articleId
     * @return
     */
    @Override
    public Result delete(Long articleId)
    {
        try
        {
            /**
             * 删除solr数据
             */
            this.searchFeignClient.deleteArticleToSolr(articleId);

            /**
             * 删除首页总文章个数缓存
             */
            this.client.deleteArticleSumToRedis();

            /**
             * 删除首页标签文章个数
             */
            this.client.deleteIndexArticleLabel();

            /**
             * 删除文章分类个数
             */
            this.client.deleteIndexSortsAllToRedis();

            /**
             * 删除首页文章缓存
             */
            this.client.deleteIndexArticle();

            /**
             * 删除文章标签总数
             */
            this.client.deleteLabelCount();

            /**
             * 删除推荐文章
             */
            this.client.deleteArticleNewRecommend();

            /**
             * 删除分类文章缓存
             */
            XiaoxiaoArticles xiaoxiaoArticles= (XiaoxiaoArticles)this.findArticleById(articleId).getData();
            this.client.deleteBlogsBySortsToRedis(xiaoxiaoArticles.getArticleBkSortsId());

            /**
             * 删除标签缓存
             */
            this.client.deleteArticleSortSum(xiaoxiaoArticles.getArticleBkSortsId());

            /**
             * 删除标签缓存数据
             *
             * 1.获取文章的所有标签数据
             * 2.删除标签文章缓存
             * 3.删除标签文章总数
             */
            List<XiaoxiaoSetArtitleLabel> articleLabel = this.setArticleLabelMapper.findArticleLabel(articleId);
            for (XiaoxiaoSetArtitleLabel x:articleLabel
                 )
            {
                /**
                 * 1.删除文章标签缓存
                 */
                 this.client.deleteArticleLabelSum(x.getLabelId());

                /**
                 * 2.删除文章标签总数
                 */
                this.client.deleteArticleByLabelId(x.getLabelId());
            }

            /**
             * 3.
             * 删除中间表数据
             */
            this.setArticleLabelMapper.deleteArticleLabelByArticleId(articleId);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        if (this.artcleMapper.delete(articleId) > 0)
        {
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 查找一个文章
     * @param articleId
     * @return
     */
    @Override
    public Result findArticleById(Long articleId)
    {
        XiaoxiaoArticles xiaoxiaoArticles = this.artcleMapper.findArticleById(articleId);
        if (xiaoxiaoArticles != null)
        {
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS, xiaoxiaoArticles);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 插入
     *
     * @param xiaoxiaoArticles
     * @return
     */
    @Override
    public Result insert(XiaoxiaoArticles xiaoxiaoArticles)
    {

        xiaoxiaoArticles.setArticleId(IDUtils.genItemId());
        xiaoxiaoArticles.setArticleDate(new Date());
        String[] split = xiaoxiaoArticles.getArticleLabel().split(",");
        /**
         * 插入中间表的数据
         */
        this.insertArticleLabel(xiaoxiaoArticles.getArticleId(), split);

        if (this.artcleMapper.insert(xiaoxiaoArticles) > 0)
        {
            try
            {
                /**
                 * 将此文章加入到的solr中
                 */
                this.searchFeignClient.insertArticleToSolr(xiaoxiaoArticles.getArticleId());

                /**
                 * 删除首页文章缓存
                 */
                this.client.deleteIndexArticle();

                /**
                 * 删除Sort文章缓存
                 */
                this.client.deleteBlogsBySortsToRedis(xiaoxiaoArticles.getArticleBkSortsId());

                /**
                 * 删除标签缓存
                 */
                this.client.deleteArticleSortSum(xiaoxiaoArticles.getArticleBkSortsId());

                /**
                 * 删除标签缓存数据
                 */
                for (String s: split
                )
                {

                    /**
                     *删除标签文章个数
                     */
                    this.client.deleteArticleLabelSum(Long.parseLong(s));

                    /**
                     * 删除
                     */
                    this.client.deleteArticleByLabelId(Long.parseLong(s));
                }
                /**
                 * 删除首页总文章个数缓存
                 */
                this.client.deleteArticleSumToRedis();
                /**
                 * 珊瑚首页标签文章个数
                 */
                this.client.deleteIndexArticleLabel();




                /**
                 * 删除文章分类个数
                 */
                this.client.deleteIndexSortsAllToRedis();

            } catch (Exception e)
            {
                e.printStackTrace();
            }

            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 中间表数据的插入
     * @param articleId
     * @param labelId
     */
    public void insertArticleLabel(Long articleId,String[] labelId){
        for (String s: labelId){
            this.setArticleLabelMapper.insert(new XiaoxiaoSetArtitleLabel(articleId,Long.parseLong(s)));
        }
    }


    /**
     * 修改文章
     * @param xiaoxiaoArticles
     * @return
     */
    @Override
    public Result update(XiaoxiaoArticles xiaoxiaoArticles)
    {
        if (this.artcleMapper.update(xiaoxiaoArticles) > 0)
        {
            try
            {
                /**
                 * 删除redis缓存的数据的文章的数据
                 */
                this.client.deleteIndexArticle();

                /**
                 * 删除Sort文章缓存
                 */
                this.client.deleteBlogsBySortsToRedis(xiaoxiaoArticles.getArticleBkSortsId());


            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }




    /**
     * 根据分类，标题，是否推荐查询的信息
     * @param page
     * @param rows
     * @param xiaoxiaoArticles
     * @return
     */
    @Override
    public Result findArticleByTitleOrSorts(Integer page, Integer rows, XiaoxiaoArticles xiaoxiaoArticles)
    {
        PageHelper.startPage(page, rows);
        List<XiaoxiaoArticleVo> articles = this.artcleMapper.findArticleByTitleOrSorts(xiaoxiaoArticles);
        if(articles != null && articles.size() > 0){
            return Result.ok(StatusCode.OK, true,PageUtils.getResult(new PageInfo<XiaoxiaoArticleVo>(articles), page));
        }
        return  Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }


    /**
     * 修改文章的状态
     * @param articleId
     * @param articleRecommend
     * @return
     */
    @Override
    public Result updateRecommend(Long articleId, String articleRecommend)
    {
        if(this.artcleMapper.updateRecommend(articleId,articleRecommend) > 0){
            return Result.ok(StatusCode.OK, this.MARKED_WORDS_SUCCESS);
        }
        return Result.error(StatusCode.ERROR, this.MARKED_WORDS_FAULT);
    }
}
