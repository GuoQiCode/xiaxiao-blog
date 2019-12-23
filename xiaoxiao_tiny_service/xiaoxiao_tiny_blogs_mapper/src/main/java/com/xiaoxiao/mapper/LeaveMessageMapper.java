package com.xiaoxiao.mapper;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.pojo.XiaoxiaoVisit;
import com.xiaoxiao.provider.XiaoxiaoLeaveMessageProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.PropertySource;
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
 * @date:2019/12/22:11:16
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface LeaveMessageMapper
{
    /**
     * 分页查询
     * @return
     */
    @Select("SELECT * FROM xiaoxiao_leave_message WHERE message_parent_id = '-1' ORDER BY message_date DESC")
    List<XiaoxiaoLeaveMessage> findAllLeaveMessage();

    /**
     * 删除
     * @param xiaoxiaoLeaveMessage
     * @return
     */
    @Delete("DELETE FROM xiaoxiao_leave_message WHERE  message_id = #{xiaoxiaoLeaveMessage.messageId}")
    int delete(@Param("xiaoxiaoLeaveMessage") XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage);

    /**
     * 查找一个
     * @param xiaoxiaoLeaveMessage
     * @return
     */
    @Select("SELECT * FROM xiaoxiao_leave_message WHERE  message_id = #{xiaoxiaoLeaveMessage.messageId}")
    XiaoxiaoLeaveMessage findOne(@Param("xiaoxiaoLeaveMessage") XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage);


    /**
     * 修改
     * @param xiaoxiaoLeaveMessage
     * @return
     */
    @UpdateProvider(type = XiaoxiaoLeaveMessageProvider.class,method = "update")
    int update(@Param("xiaoxiaoLeaveMessage") XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage);


    /**
     * 插入
     * @param xiaoxiaoLeaveMessage
     * @return
     */
    @InsertProvider(type = XiaoxiaoLeaveMessageProvider.class,method = "insert")
    int insert(@Param("xiaoxiaoLeaveMessage") XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage);
}
