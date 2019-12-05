package com.xiaoxiao.mapper;

import com.xiaoxiao.pojo.XiaoxiaoLabels;
import com.xiaoxiao.provider.LabelsProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.awt.peer.LabelPeer;
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
 * @date:2019/11/29:11:15
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface LabelMapper
{
    @Select("SELECT * FROM `xiaoxiao_borker`.`xiaoxiao_labels`")
    List<XiaoxiaoLabels> findAllLabel();

    @UpdateProvider(type = LabelsProvider.class,method = "update")
    int update(@Param("labels") XiaoxiaoLabels labels);

    @Select("SELECT * FROM `xiaoxiao_borker`.`xiaoxiao_labels` WHERE label_id = #{labelId}")
    XiaoxiaoLabels findLabelById(@Param("labelId") Long labelId);

    @Delete("DELETE FROM `xiaoxiao_borker`.`xiaoxiao_labels` WHERE `label_id` = #{labelId}")
    int delete(@Param("labelId") Long labelId);

    @InsertProvider(type = LabelsProvider.class,method = "insert")
    int insert(@Param("labels") XiaoxiaoLabels labels);
}
