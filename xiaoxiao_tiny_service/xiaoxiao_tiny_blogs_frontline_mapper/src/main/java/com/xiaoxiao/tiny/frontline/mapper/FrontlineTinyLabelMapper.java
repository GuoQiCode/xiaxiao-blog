package com.xiaoxiao.tiny.frontline.mapper;

import com.xiaoxiao.pojo.XiaoxiaoArticles;
import com.xiaoxiao.pojo.XiaoxiaoLabels;
import com.xiaoxiao.pojo.vo.XiaoxiaoArticleVo;
import com.xiaoxiao.pojo.vo.XiaoxiaoLabelVo;
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
 * @date:2019/12/3:15:11
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface FrontlineTinyLabelMapper
{
    /**
     * 查询首页标签文章数据
     * @return
     */
    @Select("SELECT SUM(1) sum, x.label_name,x.label_id FROM xiaoxiao_articles a,xiaoxiao_set_artitle_label l,xiaoxiao_labels x WHERE a.article_id = l.article_id AND x.label_id = l.label_id GROUP BY l.label_id")
    List<XiaoxiaoLabelVo>findIndexLabelArticle();

    /**
     * 查看全部的标签
     * @return
     */
    @Select("SELECT * FROM xiaoxiao_labels")
    List<XiaoxiaoLabels> findAllLabel();


    /**
     * 获取全部的标签的信息
     * @return
     */
    @Select("select count(label_id) sum from xiaoxiao_labels where label_id in (select distinct a.label_id from xiaoxiao_set_artitle_label as  a)")
    XiaoxiaoLabelVo count();


    /**
     * 根据指定的标签查询文章
     * @param labelId
     * @return
     */
    @Select("select a.*,c.user_nickname,c.user_profile_photo,d.label_name from xiaoxiao_articles as a,xiaoxiao_set_artitle_label as b,xiaoxiao_users c,xiaoxiao_labels d where a.article_id = b.article_id and a.user_id = c.user_id and b.label_id = d.label_id and b.label_id = #{labelId}")
    List<XiaoxiaoArticleVo> findArticleByLabelId(@Param("labelId") Long labelId);



    /**
     * 获取文章的标签数据
     * @param articleId
     * @return
     */
    @Select("select label_name from xiaoxiao_labels as a,xiaoxiao_set_artitle_label as b where a.label_id = b.label_id and  b.article_id = #{articleId}")
    List<XiaoxiaoLabels> findArticleLabelName(@Param("articleId") Long articleId);
}
