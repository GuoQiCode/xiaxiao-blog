package com.xiaoxiao.tiny.frontline.mapper;

import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoLabelVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
     * 获取首页推荐文章
     * @return
     */
    @Select("SELECT a.article_title,a.article_id,a.article_bk_first_img,a.article_date,a.article_desc FROM xiaoxiao_articles a WHERE a.article_recommend ='on' ORDER BY a.article_date DESC")
    List<XiaoxiaoArticles> gainTechniqueSharingArticleRecommend();


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
    @Select("select  b.article_id,b.article_title,date_format(b.article_date,'%m-%d') as year,b.article_type  from xiaoxiao_articles as b where date_format(b.article_date,'%Y') = #{year} order by  year desc")
    List<XiaoxiaoArticleVo> findArticleOfYear(@Param("year") String year);

    /**
     * 查询标签文章
     * @param labelId
     * @return
     */
    @Select("select a.*,c.user_nickname,c.user_profile_photo from xiaoxiao_articles as a,xiaoxiao_set_artitle_label as b,xiaoxiao_users as c where a.article_id = b.article_id and c.user_id = a.user_id and b.label_id = #{labelId}")
    List<XiaoxiaoArticleVo> findArticleByLabelId(@Param("labelId") Long labelId);


    /**
     * 获取分类文章的个数
     * @param sortId
     * @return
     */
    @Select("select b.sort_name,count(b.sort_id) sum from xiaoxiao_articles as a,xiaoxiao_sorts as b where a.article_bk_sorts_id = b.sort_id group by b.sort_id having b.sort_id = #{sortId}")
    XiaoxiaoSortsVo findArticleBySortSum(@Param("sortId") Long sortId);


    /**
     * 获取标签文章个数
     * @param labelId
     * @return
     */
    @Select("select b.label_name,count(b.label_id) sum from xiaoxiao_set_artitle_label as a,xiaoxiao_labels as b where a.label_id = b.label_id group by b.label_id having b.label_id = #{labelId}")
    XiaoxiaoLabelVo findArticleLabelSum(@Param("labelId") Long labelId);


    /**
     * 修改文章的浏览量
     * @param x
     * @param o
     */
    @Update("update xiaoxiao_articles as a set a.article_views = #{o} where  a.article_id = #{x} ")
    void updateArticleViews(@Param("x") Object x, @Param("o") Object o);
}
