package com.xiaoxiao.provider;

import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
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
 * @date:2019/12/3:20:15
 * @author:shinelon
 * @Describe:
 */
public class AdminManagerProvider
{


    public String update(final XiaoxiaoAdminMessage message){
        return new SQL(){
            {
                UPDATE("xiaoxiao_admin_manager");
                if(message.getAdminName() != null && message.getAdminName() != "" )
                    SET("admin_name = #{message.adminName}");
                if(message.getAdminUrl() !=null && message.getAdminUrl() != "")
                    SET("admin_url = #{message.adminUrl}");
                WHERE("admin_id = #{message.adminId}");
            }
        }.toString();
    }




    public String insert(final XiaoxiaoAdminMessage message){
        return new SQL(){
            {
                INSERT_INTO("xiaoxiao_admin_manager");
                if(message.getAdminId() != null)
                    VALUES("admin_id", "#{message.adminId}");
                if(message.getAdminName() != null && message.getAdminName() != "")
                    VALUES("admin_name", "#{message.adminName}");
                if(message.getAdminUrl() != null && message.getAdminUrl() != "")
                    VALUES("admin_url", "#{message.adminUrl}");
            }
        }.toString();
    }
}
