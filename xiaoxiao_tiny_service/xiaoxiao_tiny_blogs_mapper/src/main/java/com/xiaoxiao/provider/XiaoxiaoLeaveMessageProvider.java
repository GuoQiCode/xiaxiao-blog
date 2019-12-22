package com.xiaoxiao.provider;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
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
 * @date:2019/12/22:11:51
 * @author:shinelon
 * @Describe:
 */
public class XiaoxiaoLeaveMessageProvider
{


    public String insert(final XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage){
        return new SQL(){
            {
                INSERT_INTO("xiaoxiao_leave_message");
                if(xiaoxiaoLeaveMessage.getMessageId() != null && xiaoxiaoLeaveMessage.getMessageId() !=""){
                    VALUES("message_id", "#{xiaoxiaoLeaveMessage.messageId}");
                }
                if (xiaoxiaoLeaveMessage.getMessageNickname() != null && xiaoxiaoLeaveMessage.getMessageNickname() != ""){
                    VALUES("message_nickname ","#{xiaoxiaoLeaveMessage.messageNickname}");
                }
                if (xiaoxiaoLeaveMessage.getMessageHeadPortrait() != null && xiaoxiaoLeaveMessage.getMessageHeadPortrait() != ""){
                    VALUES("message_head_portrait ","#{xiaoxiaoLeaveMessage.messageHeadPortrait}");
                }
                if (xiaoxiaoLeaveMessage.getMessageContent() != null && xiaoxiaoLeaveMessage.getMessageContent() != ""){
                    VALUES("message_content ","#{xiaoxiaoLeaveMessage.messageContent}");
                }
                if (xiaoxiaoLeaveMessage.getMessageDate() != null ){
                    VALUES("message_date ","#{xiaoxiaoLeaveMessage.messageDate}");
                }
                if (xiaoxiaoLeaveMessage.getMessageIp() != null && xiaoxiaoLeaveMessage.getMessageIp() != ""){
                    VALUES("message_ip "," #{xiaoxiaoLeaveMessage.messageIp}");
                }
                if (xiaoxiaoLeaveMessage.getMessageEmail() != null && xiaoxiaoLeaveMessage.getMessageEmail() != ""){
                    VALUES("message_email ","#{xiaoxiaoLeaveMessage.messageEmail}");
                }
                if (xiaoxiaoLeaveMessage.getMessageParentId() != null && xiaoxiaoLeaveMessage.getMessageParentId() != ""){
                    VALUES("message_parent_id ","#{xiaoxiaoLeaveMessage.messageParentId}");
                }
            }
        }.toString();
    }



    public String update(final XiaoxiaoLeaveMessage xiaoxiaoLeaveMessage){
        return new SQL(){
            {
                UPDATE("xiaoxiao_leave_message");
                if (xiaoxiaoLeaveMessage.getMessageNickname() != null && xiaoxiaoLeaveMessage.getMessageNickname() != ""){
                    SET("message_nickname = #{xiaoxiaoLeaveMessage.messageNickname}");
                }
                if (xiaoxiaoLeaveMessage.getMessageHeadPortrait() != null && xiaoxiaoLeaveMessage.getMessageHeadPortrait() != ""){
                    SET("message_head_portrait = #{xiaoxiaoLeaveMessage.messageHeadPortrait}");
                }
                if (xiaoxiaoLeaveMessage.getMessageContent() != null && xiaoxiaoLeaveMessage.getMessageContent() != ""){
                    SET("message_content = #{xiaoxiaoLeaveMessage.messageContent}");
                }
                if (xiaoxiaoLeaveMessage.getMessageDate() != null ){
                    SET("message_date = #{xiaoxiaoLeaveMessage.messageDate}");
                }
                if (xiaoxiaoLeaveMessage.getMessageIp() != null && xiaoxiaoLeaveMessage.getMessageIp() != ""){
                    SET("message_ip = #{xiaoxiaoLeaveMessage.messageIp}");
                }
                if (xiaoxiaoLeaveMessage.getMessageEmail() != null && xiaoxiaoLeaveMessage.getMessageEmail() != ""){
                    SET("message_email = #{xiaoxiaoLeaveMessage.messageEmail}");
                }
                if (xiaoxiaoLeaveMessage.getMessageParentId() != null && xiaoxiaoLeaveMessage.getMessageParentId() != ""){
                    SET("message_parent_id = #{xiaoxiaoLeaveMessage.messageParentId}");
                }
                WHERE("message_id = #{xiaoxiaoLeaveMessage.messageId}");
            }
        }.toString();
    }
}
