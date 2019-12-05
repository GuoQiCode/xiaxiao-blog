package com.xiaoxiao.provider;

import com.xiaoxiao.pojo.XiaoxiaoUsers;
import jdk.nashorn.internal.objects.NativeUint8Array;
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
 * @date:2019/12/4:15:00
 * @author:shinelon
 * @Describe:
 */
public class UsersProvider
{
    public String update(final XiaoxiaoUsers users)
    {
        return new SQL()
        {
            {
                UPDATE("xiaoxiao_users");
                if(users.getUserIp() != null && users.getUserIp() !="")
                    SET("user_ip = #{users.userIp}");
                if(users.getUserName() != null && users.getUserName() != "")
                    SET("user_name = #{users.userName}");
                if(users.getUserPassword() != null && users.getUserPassword() != "")
                    SET("user_password = #{users.userPassword}");
                if(users.getUserEmail() != null && users.getUserEmail() != "")
                    SET("user_email = #{users.userEmail}");
                if(users.getUserProfilePhoto() != null && users.getUserProfilePhoto() != "")
                    SET("user_profile_photo = #{users.userProfilePhoto}");
                if(users.getUserAge() != null)
                    SET("user_age = #{users.userAge}");
                if(users.getUserTelephoneNumber() != null)
                    SET("user_telephone_number = #{users.userTelephoneNumber}");
                if(users.getUserNickname() != null && users.getUserNickname() != "")
                    SET("user_nickname = #{users.userNickname}");
                if(users.getUserSignature() != null && users.getUserSignature() != "")
                    SET("user_signature = #{users.userSignature}");
                if(users.getUserRealName() != null && users.getUserRealName() != "")
                    SET("user_real_name = #{users.userRealName}");
                if(users.getUserSex() != null && users.getUserSex() != null)
                    SET("user_sex = #{users.userSex}");

                WHERE("user_id = #{users.userId}");
            }
        }.toString();
    }
}
