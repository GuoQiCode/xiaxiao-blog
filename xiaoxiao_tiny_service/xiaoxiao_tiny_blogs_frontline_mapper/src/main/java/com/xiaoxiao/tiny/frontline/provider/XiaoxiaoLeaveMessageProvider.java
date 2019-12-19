package com.xiaoxiao.tiny.frontline.provider;

import com.xiaoxiao.pojo.XiaoxiaoLeaveMessage;
import org.apache.ibatis.jdbc.SQL;

/**
 * _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\| |// `. / \\||| : |||// \ / _||||| -:- |||||- \ |
 * | \\\ - /// | | | \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ ."" '< `.___\_<|>_/___.' >'"". | |
 * : `- \`.;`\ _ /`;.`/ - ` : | | \ \ `-. \_ __\ /__ _/ .-` / / ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰: 写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。 但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/19:15:01
 * @author:shinelon
 * @Describe:
 */
public class XiaoxiaoLeaveMessageProvider {

    public String insert(final XiaoxiaoLeaveMessage leaveMessage) {
        return new SQL() {
            {
                INSERT_INTO("xiaoxiao_leave_message");
                if (leaveMessage.getMessageId() != null && leaveMessage.getMessageId() != "") {
                    VALUES("message_id", "#{leaveMessage.messageId}");
                }
                if (leaveMessage.getMessageContent() != null && leaveMessage.getMessageContent() != "") {
                    VALUES("message_content", "#{leaveMessage.messageContent}");
                }
                if (leaveMessage.getMessageIp() != null && leaveMessage.getMessageIp() != "") {
                    VALUES("message_ip", "#{leaveMessage.messageIp}");
                }
                if (leaveMessage.getMessageDate() != null) {
                    VALUES("message_date", "#{leaveMessage.messageDate}");
                }
                if (leaveMessage.getMessageHeadPortrait() != null && leaveMessage.getMessageHeadPortrait() != "") {
                    VALUES("message_head_portrait", "#{leaveMessage.messageHeadPortrait}");
                }
                if (leaveMessage.getMessageNickname() != null && leaveMessage.getMessageNickname() != "") {
                    VALUES("message_nickname", "#{leaveMessage.messageNickname}");
                }
                if(leaveMessage.getMessageEmail() != null && leaveMessage.getMessageEmail() != ""){
                    VALUES("message_email", "#{leaveMessage.messageEmail}");
                }
                if (leaveMessage.getMessageParentId() != null && leaveMessage.getMessageParentId() != "") {
                    VALUES("message_parent_id", "#{leaveMessage.messageParentId}");
                }
            }
        }.toString();
    }

}
