package com.xiaoxiao.tiny.frontline.mapper;

import com.xiaoxiao.pojo.XiaoxiaoArticles;
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
 * @date:2019/12/2:20:59
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface FrontlineTinyArticleMapper
{
    /**
     * 查询全部的文章个数
     * @return
     */
    @Select("SELECT COUNT(*) sum FROM xiaoxiao_articles")
    XiaoxiaoArticleVo findArticleSum();


    /**
     * 查询推荐文章
     * @return
     */
    @Select("SELECT a.article_title,a.article_id FROM xiaoxiao_articles a WHERE a.article_recommend ='on' ORDER BY a.article_date DESC")
    List<XiaoxiaoArticles> findArticleNewRecommend();


    /**
     * 查询首页文章
     * @return
     */
    @Select("SELECT a.article_id,a.article_title,a.article_desc,a.article_date,a.article_bk_first_img,a.article_views,u.user_nickname,u.user_profile_photo FROM `xiaoxiao_articles` a,xiaoxiao_users u WHERE a.user_id = u.user_id ORDER BY a.article_date DESC")
    List<XiaoxiaoArticleVo> findIndexArticle();



    /**
     * 根据分类查询文章
     * @param sortId
     * @return
     */
    @Select("SELECT a.article_id,a.article_title,a.article_desc,a.article_date,a.article_bk_first_img,a.article_views,u.user_nickname,u.user_profile_photo FROM `xiaoxiao_articles` a,xiaoxiao_users u WHERE a.user_id = u.user_id AND a.article_bk_sorts_id = #{sortId} ORDER BY a.article_date DESC")
    List<XiaoxiaoArticleVo> findBlogsBySorts(@Param("sortId") Long sortId);


    /**
     * 获取文章信息根据文章ID
     * @param articleId
     * @return
     */
    @Select("select a.*,b.user_nickname,b.user_qr_code from xiaoxiao_articles as a,xiaoxiao_users as b where a.user_id = b.user_id and a.article_id = #{articleId}")
    XiaoxiaoArticleVo findBlogById(@Param("articleId") Long articleId);


    /**
     * 获取文章的年份
     * @return
     */
    @Select("select date_format(a.article_date,'%Y') as year from  xiaoxiao_articles as a group by a.article_date order by  a.article_date desc")
    List<String> findArticleYear();


    /**
     * 获取指定年份的文章
     * @param year
     * @return
     */
    @Select("select  b.article_id,b.article_title,date_format(b.article_date,'%m-%d') as year,b.article_type  from xiaoxiao_articles as b where date_format(b.article_date,'%Y') = #{year}")
    List<XiaoxiaoArticleVo> findArticleOfYear(@Param("year") String year);
}
