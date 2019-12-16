package com.xiaoxiao.mapper;

import com.xiaoxiao.pojo.XiaoxiaoSetArtitleLabel;
import com.xiaoxiao.provider.SetArticleLabelProvider;
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
 * @date:2019/12/3:13:57
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface SetArticleLabelMapper
{

    /**
     *插入文章标签关联表
     * @param artitleLabel
     * @return
     */
    @InsertProvider(type = SetArticleLabelProvider.class,method = "insert")
    int insert(@Param("articleLabel")XiaoxiaoSetArtitleLabel artitleLabel);


    /**
     * 删除文章标签数据
     * @param articleId
     */
    @Delete("delete from xiaoxiao_set_artitle_label where article_id = #{articleId}")
    void deleteArticleLabelByArticleId(@Param("articleId") Long articleId);


    /**
     * 获取文章的所有标签
     * @param articleId
     * @return
     */
    @Select("select * from xiaoxiao_set_artitle_label as a where a.article_id = #{articleId}")
    List<XiaoxiaoSetArtitleLabel> findArticleLabel(@Param("articleId")Long articleId);
}
