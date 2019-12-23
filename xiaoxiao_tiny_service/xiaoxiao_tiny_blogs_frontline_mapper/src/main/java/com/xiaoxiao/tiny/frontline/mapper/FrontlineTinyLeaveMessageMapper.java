package com.xiaoxiao.tiny.frontline.mapper;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import com.xiaoxiao.pojo.vo.XiaoxiaoLeaveMessageVo;
import com.xiaoxiao.tiny.frontline.provider.XiaoxiaoLeaveMessageProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
 * @date:2019/12/19:14:57
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface FrontlineTinyLeaveMessageMapper
{

    /**
     * 插入
     * @param leaveMessage
     * @return
     */
    @InsertProvider(type = XiaoxiaoLeaveMessageProvider.class,method = "insert")
    int insert(@Param("leaveMessage") XiaoxiaoLeaveMessage leaveMessage);

    /**
     * 分页查询
     * @return
     */
    @Select("SELECT * FROM xiaoxiao_leave_message WHERE message_parent_id = '-1' ORDER BY message_date DESC")
    List<XiaoxiaoLeaveMessage> findAllLeaveMessage();


    /**
     * 获取的
     * @return
     */
    @Select("select count(a.message_id) sum from  xiaoxiao_leave_message as a")
    XiaoxiaoLeaveMessageVo getLeaveMessageSum();


    /**
     * 获取子评论
     * @return
     */
    @Select("select * from xiaoxiao_leave_message as a where  a.message_parent_id = #{messageId}")
    List<XiaoxiaoLeaveMessage> getSon(@Param("messageId") String messageId);

}
