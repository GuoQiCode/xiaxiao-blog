package com.xiaoxiao.search.mapper;

import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
 * @date:2019/12/13:13:55
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface SearchArticleMapper
{
    /**
     * 查询全部的数据
     * @return
     */
    @Select("select a.article_id,a.article_recommend,a.user_id,a.article_title,a.article_views,a.article_comment_count,a.article_date,a.article_like_count,a.article_bk_first_img,a.article_bk_sorts_id,a.article_bk_first_img,a.article_desc,a.article_type,b.user_nickname,b.user_profile_photo from xiaoxiao_articles as a,xiaoxiao_users as b where a.user_id = b.user_id")
    List<XiaoxiaoArticleVo> findAllArticle();


    /**
     * 查询一个文章
     * @param articleId
     * @return
     */
    @Select("select a.article_id," +
            "a.article_recommend," +
            "a.user_id,a.article_title" +
            ",a.article_views," +
            "a.article_comment_count," +
            "a.article_date," +
            "a.article_like_count," +
            "a.article_bk_first_img," +
            "a.article_bk_sorts_id," +
            "a.article_bk_first_img," +
            "a.article_desc,a.article_type," +
            "b.user_nickname,b.user_profile_photo " +
            "from xiaoxiao_articles as a,xiaoxiao_users as b " +
            "where a.user_id = b.user_id and a.article_id = #{articleId}")
    List<XiaoxiaoArticleVo> findArticleById(@Param("articleId")Long articleId);
}
