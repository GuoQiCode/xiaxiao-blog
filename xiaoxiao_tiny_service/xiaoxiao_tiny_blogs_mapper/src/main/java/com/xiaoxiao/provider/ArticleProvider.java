package com.xiaoxiao.provider;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.xiaoxiao.pojo.XiaoxiaoArticles;
import org.apache.ibatis.jdbc.SQL;

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
 * @date:2019/11/30:17:00
 * @author:shinelon
 * @Describe:
 */
public class ArticleProvider
{


    /**
     * 修改文章是否推荐
     * @param articleId
     * @param articleRecommend
     * @return
     */
    public String updateRecommend(final Long articleId,final String articleRecommend)
    {
        return new SQL(){
            {
                UPDATE("xiaoxiao_articles");
                if (articleRecommend != null && articleRecommend != "")SET("article_recommend = #{articleRecommend}");
                WHERE("article_id = #{articleId}");
            }
        }.toString();
    }





    /**
     * 根据分类，标题，是否推荐查询的信息
     *
     * @param articles
     * @return
     */
    public String findArticleByTitleOrSorts(final XiaoxiaoArticles articles)
    {
        return new SQL()
        {
            {
                SELECT("a.article_id,a.article_title,a.article_views,a.article_comment_count,a.article_date,a.article_like_count,a.article_recommend,b.sort_id,b.sort_name");
                FROM("`xiaoxiao_articles` as a", "xiaoxiao_sorts b");
                WHERE("WHERE a.article_bk_sorts_id = b.sort_id");
                if (articles.getArticleTitle() != null && articles.getArticleTitle() != "")
                    WHERE("a.article_title = #{articles.articleTitle}");
                if (articles.getArticleRecommend() != null && articles.getArticleRecommend() != "")
                    WHERE("a.article_recommend = #{articles.articleRecommend}");
                if (articles.getArticleBkSortsId() != null && articles.getArticleBkSortsId() > 0)
                    WHERE("b.sort_id = #{articles.articleBkSortsId}");
            }
        }.toString();
    }


    /**
     * 插入
     *
     * @param articles
     * @return
     */
    public String insert(final XiaoxiaoArticles articles)
    {
        return new SQL()
        {
            {
                INSERT_INTO("xiaoxiao_articles");
                if (articles.getArticleId() != null) VALUES("article_id", "#{articles.articleId}");
                if (articles.getUserId() != null) VALUES("user_id", "#{articles.userId}");
                if (articles.getArticleTitle() != null && articles.getArticleTitle() != "")
                    VALUES("article_title", "#{articles.articleTitle}");
                if (articles.getArticleContent() != null && articles.getArticleContent() != "")
                    VALUES("article_content", "#{articles.articleContent}");
                if (articles.getArticleViews() != null) VALUES("article_views", "#{articles.articleViews}");
                if (articles.getArticleCommentCount() != null)
                    VALUES("article_comment_count", "#{articles.articleCommentCount}");
                if (articles.getArticleDate() != null) VALUES("article_date", "#{articles.articleDate}");
                if (articles.getArticleLikeCount() != null)
                    VALUES("article_like_count", "#{articles.articleLikeCount}");
                if (articles.getArticleBkSortsId() != null)
                    VALUES("article_bk_sorts_id", "#{articles.articleBkSortsId}");
                if (articles.getArticleBkFirstImg() != null)
                    VALUES("article_bk_first_img", "#{articles.articleBkFirstImg}");
                if (articles.getArticleRecommend() != null && articles.getArticleRecommend() != "")
                    VALUES("article_recommend", "#{articles.articleRecommend}");
                if(articles.getArticleDesc() != null && articles.getArticleDesc() != "")
                    VALUES("article_desc","#{articles.articleDesc}");
                if(articles.getArticleType() != null && articles.getArticleType() != "")
                    VALUES("article_type", "#{articles.articleType}");
            }
        }.toString();
    }


    /**
     * 修改
     *
     * @param articles
     * @return
     */
    public String update(final XiaoxiaoArticles articles)
    {
        return new SQL()
        {
            {
                UPDATE("xiaoxiao_articles");
                if (articles.getUserId() != null)
                    SET("user_id = #{articles.userId}");
                if (articles.getArticleTitle() != null && articles.getArticleTitle() != "")
                    SET("article_title = #{articles.articleTitle}");
                if (articles.getArticleContent() != null && articles.getArticleContent() != "")
                    SET("article_content = #{articles.articleContent}");
                if (articles.getArticleViews() != null)
                    SET("article_views = #{articles.articleViews}");
                if (articles.getArticleCommentCount() != null)
                    SET("article_comment_count = #{articles.articleCommentCount}");
                if (articles.getArticleDate() != null)
                    SET("article_date = #{articles.articleDate}");
                if (articles.getArticleLikeCount() != null)
                    SET("article_like_count = #{articles.articleLikeCount}");
                if (articles.getArticleBkImg() != null) SET("article_bk_img = #{articles.articleBkImg}");
                if (articles.getArticleBkSortsId() != null)
                    SET("article_bk_sorts_id = #{articles.articleBkSortsId}");
                if (articles.getArticleBkFirstImg() != null)
                    SET("article_bk_first_img = #{articles.articleBkFirstImg}");
                if (articles.getArticleRecommend() != null && articles.getArticleRecommend() != "")
                    SET("article_recommend = #{articles.articleRecommend}");
                if(articles.getArticleDesc() != null && articles.getArticleDesc() != "")
                    SET("article_desc = #{articles.articleDesc}");
                if(articles.getArticleType() != null && articles.getArticleType() != "")
                    SET("article_type = #{articles.articleType}");

                WHERE("article_id = #{articles.articleId} ");
            }
        }.toString();
    }
}
