package com.xiaoxiao.mapper;

import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.provider.ArticleProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
@Repository
public interface ArtcleMapper
{

    @Select("SELECT a.article_id,a.article_title,a.article_views,a.article_comment_count,a.article_date,a.article_like_count,a.article_recommend,b.sort_id,b.sort_name FROM `xiaoxiao_articles` a ,xiaoxiao_sorts b WHERE a.article_bk_sorts_id = b.sort_id")
    @Results(value = {
            @Result(column = "sort_name",property = "sortName")
     }
    )
    List<XiaoxiaoArticleVo> findAllArticle();


    @Delete("DELETE FROM xiaoxiao_articles WHERE article_id = #{articleId}")
    int delete(@Param("articleId") Long articleId);



    @Select("SELECT * FROM xiaoxiao_articles WHERE article_id = #{articleId}")
    XiaoxiaoArticles findArticleById(@Param("articleId") Long articleId);



    @InsertProvider(type = ArticleProvider.class,method = "insert")
    int insert(@Param("articles") XiaoxiaoArticles articles);



    @UpdateProvider(type = ArticleProvider.class,method = "update")
    int update(@Param("articles") XiaoxiaoArticles articles);

    /**
     * 根据分类，标题，是否推荐查询的信息
     * @param articles
     * @return
     */
    @SelectProvider(type = ArticleProvider.class,method = "findArticleByTitleOrSorts")
    List<XiaoxiaoArticleVo> findArticleByTitleOrSorts(@Param("articles") XiaoxiaoArticles articles);

    /**
     * 修改文章是否推荐
     * @param articleId
     * @param articleRecommend
     * @return
     */
    @UpdateProvider(type = ArticleProvider.class,method = "updateRecommend")
    int updateRecommend(@Param("articleId") Long articleId,@Param("articleRecommend") String articleRecommend);
}
