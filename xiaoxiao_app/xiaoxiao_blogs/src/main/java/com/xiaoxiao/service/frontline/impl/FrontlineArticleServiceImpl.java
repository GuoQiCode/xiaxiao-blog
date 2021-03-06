package com.xiaoxiao.service.frontline.impl;

import com.xiaoxiao.feign.FrontlineFeignServiceClient;
import com.xiaoxiao.feign.SearchFeignClient;
import com.xiaoxiao.service.frontline.FrontlineArticleService;
import com.xiaoxiao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
 * @date:2019/12/2:21:04
 * @author:shinelon
 * @Describe:
 */
@Service
public class FrontlineArticleServiceImpl implements FrontlineArticleService
{

    @Autowired
    private FrontlineFeignServiceClient frontlineFeignServiceClient;

    @Autowired
    private SearchFeignClient searchFeignClient;


    /**
     * 统计全部文章个数
     * @return
     */
    @Override
    public Result findArticleSum()
    {
        return this.frontlineFeignServiceClient.findArticleSum();
    }


    /**
     * 查询最新推荐的文章
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findArticleNewRecommend(Integer page, Integer rows)
    {
        return this.frontlineFeignServiceClient.findArticleNewRecommend(page,rows);
    }


    /**
     * 查询首页的博客文章
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findIndexArticle(Integer page, Integer rows)
    {
        return this.frontlineFeignServiceClient.findIndexArticle(page,rows);
    }


    /**
     * 根据分类请求文章
     * @param page
     * @param rows
     * @param sortId
     * @return
     */
    @Override
    public Result findBlogsBySorts(Integer page, Integer rows, Long sortId)
    {
        return this.frontlineFeignServiceClient.findBlogsBySorts(page,rows,sortId);
    }


    /**
     * 获取文章详情
     * @param articleId
     * @return
     */
    @Override
    public Result findBlogById(Long articleId)
    {
        return this.frontlineFeignServiceClient.findBlogById(articleId);
    }


    /**
     * 获取归档文章的信息
     * @return
     */
    @Override
    public Result findArticleArchive()
    {
        return this.frontlineFeignServiceClient.findArticleOfYear();
    }


    /**
     *
     * @param labelId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result findArticleByLabelId(Long labelId, Integer page, Integer rows)
    {
        return this.frontlineFeignServiceClient.findArticleByLabelId(labelId,page,rows);
    }


    /**
     *
     * @param sortId
     * @return
     */
    @Override
    public Result findArticleBySortSum(Long sortId)
    {
        return this.frontlineFeignServiceClient.findArticleBySortSum(sortId);
    }

    @Override
    public Result findArticleLabelSum(Long labelId)
    {
        return this.frontlineFeignServiceClient.findArticleLabelSum(labelId);
    }




    @Override
    public Result searchArticle(String query, Long page, Integer rows)
    {
        return searchFeignClient.searchArticle(query, page, rows);
    }
}
