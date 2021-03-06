package com.xiaoxiao.mapper;

import com.xiaoxiao.pojo.XiaoxiaoUserHobby;
import com.xiaoxiao.pojo.XiaoxiaoUsers;
import com.xiaoxiao.provider.UserHobbyProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
 * @date:2019/12/4:16:04
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface UserHobbyMapper
{
    @Delete("DELETE FROM xiaoxiao_user_hobby WHERE user_id = #{userId}")
    int delete(@Param("userId") Long userId);

    @InsertProvider(type = UserHobbyProvider.class,method = "insert")
    int insert(@Param("hobby") XiaoxiaoUserHobby hobby);


    /**
     * 修改密码
     * @param users
     * @return
     */
    @Update("update xiaoxiao_users as a set a.user_password = #{users.userPassword} where a.user_id = #{users.userId}")
    int updatePassword(@Param("users") XiaoxiaoUsers users);
}
