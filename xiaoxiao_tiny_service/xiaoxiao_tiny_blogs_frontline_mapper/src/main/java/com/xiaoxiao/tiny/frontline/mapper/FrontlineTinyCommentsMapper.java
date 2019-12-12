package com.xiaoxiao.tiny.frontline.mapper;

import com.xiaoxiao.pojo.XiaoxiaoComments;
import com.xiaoxiao.tiny.frontline.provider.CommentsProvider;
import org.apache.ibatis.annotations.InsertProvider;
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
 * @date:2019/12/11:23:16
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface FrontlineTinyCommentsMapper
{
    /**
     * 保存评论
     * @param comments
     * @return
     */
    @InsertProvider(type = CommentsProvider.class,method = "saveComments")
    int saveComments(@Param("comments") XiaoxiaoComments comments);


    /**
     * 查询指定文章的评论
     * @param articleId
     * @return
     */
    @Select("select * from xiaoxiao_comments as a where a.article_id = #{articleId} and a.parent_comment_id = -1 order by a.comment_date desc")
    List<XiaoxiaoComments> findAll(@Param("articleId")Long articleId);


    /**
     * 获取的子评论
     * @param parentId
     * @return
     */
    @Select("select * from xiaoxiao_comments as a where a.parent_comment_id = #{parentId} order by a.comment_date desc")
    List<XiaoxiaoComments> findChildComments(@Param("parentId") Long parentId);

}
