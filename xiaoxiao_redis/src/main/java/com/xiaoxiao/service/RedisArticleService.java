package com.xiaoxiao.service;

import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoLabelVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.utils.PageResult;

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
 * @date:2019/12/2:21:11
 * @author:shinelon
 * @Describe:
 */
public interface RedisArticleService
{
    void insertArticleSumToRedis(Integer sum);

    Integer getArticleSumToRedis();

    void deleteArticleSumToRedis();

    void insertArticleNewRecommend(PageResult articles);

    PageResult getArticleNewRecommend();

    void deleteArticleNewRecommend();

    void insertIndexArticle(PageResult result);

    PageResult getIndexArticle();

    void deleteIndexArticle();

    void insertBlogsBySortsToRedis(PageResult articleVos, Long sortId);

    PageResult getBlogsBySortsToRedis(Long sortId);

    void deleteBlogsBySortsToRedis(Long sortId);

    void insertArticleById(XiaoxiaoArticleVo articles);

    XiaoxiaoArticleVo getArticleById(Long articleId);

    void insertArticleArchive(Map<String, List<XiaoxiaoArticleVo>> map);

    Map<String, List<XiaoxiaoArticleVo>> getArticleArchive();

    void deleteArticleArchive();

    void insertArticleByLabelId(PageResult result, Long labelId);

    void deleteArticleByLabelId(Long labelId);

    PageResult getArticleByLabelId(Long labelId);

    /**
     * 缓存指定分类文章个数
     * @param sortId 分类ID
     * @param sortsVo
     */
    void insertArticleSortSum(Long sortId, XiaoxiaoSortsVo sortsVo);

    /**
     * 获取指定分类文章的个数
      * @param sortId
     * @return
     */
    XiaoxiaoSortsVo getArticleSortSum(Long sortId);


    /**
     * 删除的指定分类文章的个数
     * @param sortId
     */
    void deleteArticleSortSum(Long sortId);


    /**
     * 删除
     * @param labelId
     */
    void deleteArticleLabelSum(Long labelId);

    /**获取
     *
     * @return
     * @param labelId
     */
    XiaoxiaoLabelVo getArticleLabelSum(Long labelId);


    /**
     * 插入
     * @param labelId
     * @param labelVo
     */
    void insertArticleLabelSum(Long labelId, XiaoxiaoLabelVo labelVo);
}
